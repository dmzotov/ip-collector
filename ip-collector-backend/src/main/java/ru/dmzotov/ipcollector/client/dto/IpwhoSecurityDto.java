package ru.dmzotov.ipcollector.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpwhoSecurityDto {
    private boolean anonymous;

    private boolean proxy;

    private boolean vpn;

    private boolean tor;

    private boolean hosting;
}
