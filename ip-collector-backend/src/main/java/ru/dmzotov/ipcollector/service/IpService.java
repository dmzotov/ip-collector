package ru.dmzotov.ipcollector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.dmzotov.ipcollector.dto.IpDto;
import ru.dmzotov.ipcollector.dto.IpSearchRequestDto;
import ru.dmzotov.ipcollector.dto.RequestHistoryDto;
import ru.dmzotov.ipcollector.entity.Ip;

import java.util.List;

public interface IpService {
    /**
     * Get the ip address information and update it if necessary
     * @param ipString      the ip address
     * @param forceUpdate   update ip address information immediately
     * @return              the ip address information
     */
    IpDto getIp(String ipString, boolean forceUpdate);

    /**
     * Search ip addresses by the given filter
     * @param request   the filter for search request
     * @param pageable  pageable object
     * @return          ip addresses
     */
    Page<IpDto> search(IpSearchRequestDto request, Pageable pageable);

    /**
     * Search full request history for the given ip address
     * @param ipString  the ip address
     * @return          List of request history of the ip address
     */
    List<RequestHistoryDto> findRequestHistoryByIp(String ipString);

    /**
     * Create and save ip object
     * @param ipString  the ip address
     * @return          created ip object
     */
    Ip createIp(String ipString);

    /**
     * Update information about ip address
     * @param ip         the ip address object
     */
    void update(Ip ip);
}
