package ru.dmzotov.ipcollector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.dmzotov.ipcollector.dto.enums.IpSource;

import java.time.LocalDateTime;

@Data
@Schema(description = "History of requests to fetch data for IP addresses")
public class RequestHistoryDto {
    @Schema(description = "ID of the request")
    private Long id;

    @Schema(description = "Raw response")
    private String sourceData;

    @Schema(description = "The source of fetched data")
    private IpSource sourceSystem;

    @Schema(description = "Creation date and time")
    private LocalDateTime created;
}
