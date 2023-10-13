package ru.dmzotov.ipcollector.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpwhoCurrencyDto {
    private String name;

    private String code;

    private String symbol;

    private String plural;

    @JsonAlias("exchange_rate")
    private double exchangeRate;
}
