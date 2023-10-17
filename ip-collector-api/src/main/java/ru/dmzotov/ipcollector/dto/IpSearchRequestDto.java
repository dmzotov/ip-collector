package ru.dmzotov.ipcollector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Filter to search IP addresses")
public class IpSearchRequestDto {
    @Schema(description = "IP address")
    private String ip;

    @Schema(description = "Country code (2 symbols)")
    private String countryCode;

    @Schema(description = "Country full name")
    private String countryName;

    @Schema(description = "Min creation date and time")
    private LocalDateTime minCreated;

    @Schema(description = "Max creation date and time")
    private LocalDateTime maxCreated;
}
