package ru.dmzotov.ipcollector.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dmzotov.ipcollector.utils.CustomLocalDateTimeSerializer;

import java.time.LocalDateTime;

@Slf4j
@Configuration
public class ServiceConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        return new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(javaTimeModule)
                .registerModule(new PageJacksonModule())
                .registerModule(new SortJacksonModule())
                .configure(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
