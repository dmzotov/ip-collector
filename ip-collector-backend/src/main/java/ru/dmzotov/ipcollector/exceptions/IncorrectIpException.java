package ru.dmzotov.ipcollector.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectIpException extends RuntimeException {
    public IncorrectIpException(String ip) {
        super("The given ip is not valid");
        log.warn("Incorrect ip address format: {}", ip);
    }
}
