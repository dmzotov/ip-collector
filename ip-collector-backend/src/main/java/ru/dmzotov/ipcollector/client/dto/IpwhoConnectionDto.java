package ru.dmzotov.ipcollector.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpwhoConnectionDto {
    private int asn;

    private String org;

    private String isp;

    private String domain;
}
