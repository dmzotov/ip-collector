package ru.dmzotov.ipcollector.mapper;

import org.springframework.stereotype.Component;
import ru.dmzotov.ipcollector.dto.IpDto;
import ru.dmzotov.ipcollector.entity.Ip;

@Component
public class IpMapper extends AbstractMapper<Ip, IpDto> {
    public IpMapper() {
        super(Ip.class, IpDto.class);
    }
}
