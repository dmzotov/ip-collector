package ru.dmzotov.ipcollector.mapper;

import org.springframework.stereotype.Component;
import ru.dmzotov.ipcollector.dto.IpDto;
import ru.dmzotov.ipcollector.model.Ip;

@Component
public class IpMapper extends AbstractMapper<Ip, IpDto> {
    public IpMapper(Class<Ip> entityClass, Class<IpDto> dtoClass) {
        super(entityClass, dtoClass);
    }
}
