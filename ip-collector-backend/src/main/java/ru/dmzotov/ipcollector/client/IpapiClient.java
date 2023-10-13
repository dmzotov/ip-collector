package ru.dmzotov.ipcollector.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dmzotov.ipcollector.client.dto.IpapiResponseDto;

@FeignClient(name = "IpapiClient", url = "${configuration.url.ipapi}")
public interface IpapiClient {
    @GetMapping("/{ip}")
    IpapiResponseDto getIpInfo(@PathVariable String ip);
}
