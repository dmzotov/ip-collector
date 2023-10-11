package ru.dmzotov.ipcollector.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String object) {
        super(object + " is not found");
    }
}
