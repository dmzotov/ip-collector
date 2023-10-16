package ru.dmzotov.ipcollector.mapper;

import org.springframework.stereotype.Component;
import ru.dmzotov.ipcollector.dto.RequestHistoryDto;
import ru.dmzotov.ipcollector.entity.RequestHistory;

@Component
public class RequestHistoryMapper extends AbstractMapper<RequestHistory, RequestHistoryDto> {
    public RequestHistoryMapper() {
        super(RequestHistory.class, RequestHistoryDto.class);
    }
}
