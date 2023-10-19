package ru.dmzotov.ipcollector.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpwhoResponseDto {
    private String ip;

    private boolean success;

    private String type;

    private String continent;

    @JsonAlias("continent_code")
    private String continentCode;

    private String country;

    @JsonAlias("country_code")
    private String countryCode;

    private String region;

    @JsonAlias("region_code")
    private String regionCode;

    private String city;

    private String latitude;

    private String longitude;

    @JsonAlias("is_eu")
    private boolean isEu;

    private String postal;

    @JsonAlias("calling_code")
    private int callingCode;

    private String capital;

    private String borders;

    private IpwhoFlagDto flag;

    private IpwhoConnectionDto connection;

    private IpwhoTimezoneDto timezone;

    private IpwhoCurrencyDto currency;

    private IpwhoSecurityDto security;
}
