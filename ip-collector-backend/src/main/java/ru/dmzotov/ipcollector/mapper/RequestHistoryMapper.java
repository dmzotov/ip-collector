package ru.dmzotov.ipcollector.mapper;

import org.springframework.stereotype.Component;
import ru.dmzotov.ipcollector.dto.RequestHistoryDto;
import ru.dmzotov.ipcollector.model.RequestHistory;

@Component
public class RequestHistoryMapper extends AbstractMapper<RequestHistory, RequestHistoryDto> {
    public RequestHistoryMapper(Class<RequestHistory> entityClass, Class<RequestHistoryDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
