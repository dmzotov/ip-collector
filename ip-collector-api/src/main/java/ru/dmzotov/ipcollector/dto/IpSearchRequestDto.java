package ru.dmzotov.ipcollector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Filter to search IP addresses")
public class IpSearchRequestDto {
    @Schema(description = "IP addresses")
    private List<String> ip;

    @Schema(description = "Country codes (2 symbols)")
    private List<String> countryCode;

    @Schema(description = "Country full names")
    private List<String> countryNames;

    @Schema(description = "Min creation date and time")
    private LocalDateTime minCreated;

    @Schema(description = "Max creation date and time")
    private LocalDateTime maxCreated;
}
