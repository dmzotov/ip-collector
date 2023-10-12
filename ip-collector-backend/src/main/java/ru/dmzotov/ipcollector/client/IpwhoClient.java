package ru.dmzotov.ipcollector.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "IpwhoClient", url = "${configuration.url.ipwho}")
public interface IpwhoClient {
    @GetMapping("/{ip}")
    void getIp(@PathVariable String ip); // todo: return value
}
