package ru.dmzotov.ipcollector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "IP addresses")
public class IpDto {
    @Schema(description = "ID of the IP address")
    private Long id;

    @Schema(description = "IP address")
    private String ip;

    @Schema(description = "Country code (2 symbols)")
    private String countryCode;

    @Schema(description = "Country full name")
    private String countryName;

    @Schema(description = "Creation date and time")
    private LocalDateTime created;

    @Schema(description = "Update date and time")
    private LocalDateTime updated;
}
