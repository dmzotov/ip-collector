package ru.dmzotov.ipcollector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmzotov.ipcollector.dto.IpDto;
import ru.dmzotov.ipcollector.dto.IpSearchRequestDto;
import ru.dmzotov.ipcollector.dto.RequestHistoryDto;
import ru.dmzotov.ipcollector.exceptions.IncorrectIpException;
import ru.dmzotov.ipcollector.exceptions.NotFoundException;
import ru.dmzotov.ipcollector.mapper.IpMapper;
import ru.dmzotov.ipcollector.mapper.RequestHistoryMapper;
import ru.dmzotov.ipcollector.model.Ip;
import ru.dmzotov.ipcollector.repository.IpRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IpServiceImpl implements IpService {
    private static final int EXPIRE_DAYS = 30;
    private final RequestHistoryMapper requestHistoryMapper;
    private final IpRepository ipRepository;
    private final IpMapper ipMapper;

    @Override
    @Transactional
    public IpDto getIp(String ipString, boolean forceUpdate) {
        Ip ip = ipRepository.findById(getIpId(ipString)).orElse(createIp(ipString));
        if (ip.getUpdated().isBefore(LocalDateTime.now().minusDays(EXPIRE_DAYS))) {
            update(ip);
        }
        return ipMapper.toDto(ip);
    }

    @Override
    public Page<IpDto> search(IpSearchRequestDto request, Pageable pageable) {
        return ipRepository.findAll(createSpecification(request), pageable).map(ipMapper::toDto);
    }

    @Override
    public List<RequestHistoryDto> findRequestHistoryByIp(String ipString) {
        return ipRepository.findById(getIpId(ipString))
                .map(Ip::getHistory)
                .map(historyDtos -> historyDtos
                        .stream()
                        .map(requestHistoryMapper::toDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException("ip"));
    }

    private Ip createIp(String ipString) {
        Ip ip = Ip.builder()
                .id(getIpId(ipString))
                .ip(ipString)
                .build();
        update(ip);
        return ipRepository.save(ip);
    }

    private void update(Ip ip) {
        // todo
    }

    private Long getIpId(String ip) {
        if (ip == null) {
            throw new IncorrectIpException(ip);
        }
        String[] ipAddressParts = ip.split("\\.");
        if (ipAddressParts.length != 4) {
            throw new IncorrectIpException(ip);
        }

        long result = 0;
        for (int i = 0; i < ipAddressParts.length; i++) {
            int power = 3 - i;
            int ipPart = Integer.parseInt(ipAddressParts[i]);
            result += ipPart * Math.pow(256, power);
        }
        return result;
    }

    private Specification<Ip> createSpecification(IpSearchRequestDto request) {
        return Stream.of(
                        filterByText("ip", request.getIp()),
                        filterByText("countryCode", request.getCountryCode()),
                        filterByText("countryName", request.getCountryName()),
                        filterMinCreated(request.getMinCreated()),
                        filterMaxCreated(request.getMaxCreated())
                )
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse(null);
    }

    private Specification<Ip> filterByText(String field, String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }
        return (entity, cq, cb) -> cb.like(entity.get(field), "%" + text.trim() + "%");
    }

    private Specification<Ip> filterMinCreated(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return (entity, cq, cb) -> cb.greaterThanOrEqualTo(entity.get("created"), dateTime);
    }

    private Specification<Ip> filterMaxCreated(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return (entity, cq, cb) -> cb.lessThanOrEqualTo(entity.get("created"), dateTime);
    }
}
