package ru.dmzotov.ipcollector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Contains information about error")
public class ErrorDto {
    @Schema(description = "Error message")
    private String message;
}
