package ru.dmzotov.ipcollector.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dmzotov.ipcollector.client.dto.IpwhoResponseDto;

@FeignClient(name = "IpwhoClient", url = "${configuration.url.ipwho}")
public interface IpwhoClient {
    @GetMapping("/{ip}")
    IpwhoResponseDto getIpInfo(@PathVariable String ip);
}
