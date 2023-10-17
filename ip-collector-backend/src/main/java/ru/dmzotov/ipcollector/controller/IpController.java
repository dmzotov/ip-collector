package ru.dmzotov.ipcollector.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmzotov.ipcollector.dto.IpDto;
import ru.dmzotov.ipcollector.dto.IpSearchRequestDto;
import ru.dmzotov.ipcollector.dto.RequestHistoryDto;
import ru.dmzotov.ipcollector.service.IpService;

import java.util.List;

@RestController
@RequestMapping("/ip")
@RequiredArgsConstructor
public class IpController {
    private final IpService ipService;

    @GetMapping("/{ip}")
    @Operation(summary = "Get information about IP address")
    public ResponseEntity<IpDto> getIp(@PathVariable String ip) {
        return ResponseEntity.ok(ipService.getIp(ip, false));
    }

    @PutMapping("/{ip}")
    @Operation(summary = "Update information about IP address and get it")
    public ResponseEntity<IpDto> updateIp(@PathVariable String ip) {
        return ResponseEntity.ok(ipService.getIp(ip, true));
    }

    @PostMapping
    @PageableAsQueryParam
    @Operation(summary = "Search IP addresses")
    public Page<IpDto> search(@RequestBody IpSearchRequestDto request, @Parameter(hidden = true) Pageable pageable) {
        return ipService.search(request, pageable);
    }

    @GetMapping("/{ip}/history")
    @Operation(summary = "Get update history of IP address")
    public ResponseEntity<List<RequestHistoryDto>> getHistory(@PathVariable String ip) {
        return ResponseEntity.ok(ipService.findRequestHistoryByIp(ip));
    }
}
