package ru.dmzotov.ipcollector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.dmzotov.ipcollector.dto.IpDto;
import ru.dmzotov.ipcollector.dto.IpSearchRequestDto;
import ru.dmzotov.ipcollector.dto.RequestHistoryDto;

import java.util.List;
import java.util.Optional;

public interface IpService {
    /**
     * Get the ip address information and update it if necessary
     * @param ip            the ip address
     * @param forceUpdate   update ip address information immediately
     * @return              the ip address information
     */
    Optional<IpDto> getIp(String ip, boolean forceUpdate);

    /**
     * Search ip addresses by the given filter
     * @param request   the filter for search request
     * @param pageable  pageable object
     * @return          ip addresses
     */
    Page<IpDto> search(IpSearchRequestDto request, Pageable pageable);

    /**
     * Search full request history for the given ip address
     * @param ip    the ip address
     * @return      List of request history of the ip address
     */
    List<RequestHistoryDto> findRequestHistoryByIp(String ip);
}
