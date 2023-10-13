package ru.dmzotov.ipcollector.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpwhoFlagDto {
    private String img;

    private String emoji;

    @JsonAlias("emoji_unicode")
    private String emojiUnicode;
}
