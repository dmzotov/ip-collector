package ru.dmzotov.ipcollector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IpServiceImpl implements IpService {
    private final RequestHistoryMapper requestHistoryMapper;
    private final IpRepository ipRepository;
    private final IpMapper ipMapper;

    @Override
    public Optional<IpDto> getIp(String ip, boolean forceUpdate) {
        return ipRepository.findById(getIpId(ip)).map(ipMapper::toDto);
    }

    @Override
    public Page<IpDto> search(IpSearchRequestDto request, Pageable pageable) {
        return null;
    }

    @Override
    public List<RequestHistoryDto> findRequestHistoryByIp(String ip) {
        return ipRepository.findById(getIpId(ip))
                .map(Ip::getHistory)
                .map(historyDtos -> historyDtos
                        .stream()
                        .map(requestHistoryMapper::toDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException("ip"));
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
}
