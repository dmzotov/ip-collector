package ru.dmzotov.ipcollector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.dmzotov.ipcollector.client.IpapiClient;
import ru.dmzotov.ipcollector.client.IpwhoClient;
import ru.dmzotov.ipcollector.client.dto.IpapiResponseDto;
import ru.dmzotov.ipcollector.client.dto.IpwhoResponseDto;
import ru.dmzotov.ipcollector.dto.IpDto;
import ru.dmzotov.ipcollector.entity.Ip;
import ru.dmzotov.ipcollector.entity.RequestHistory;
import ru.dmzotov.ipcollector.exceptions.IncorrectIpException;
import ru.dmzotov.ipcollector.mapper.IpMapper;
import ru.dmzotov.ipcollector.mapper.RequestHistoryMapper;
import ru.dmzotov.ipcollector.repository.IpRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IpServiceTest {
    private static final String TEST_IP = "8.8.8.8";
    private static final Long TEST_IP_ID = 134744072L;

    @Mock
    private RequestHistoryMapper requestHistoryMapper;
    @Mock
    private IpRepository ipRepository;
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private IpwhoClient ipwhoClient;
    @Mock
    private IpapiClient ipapiClient;
    @Mock
    private IpMapper ipMapper;
    @InjectMocks
    private IpServiceImpl ipService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(ipService, "ipService", ipService);
    }

    @Test
    public void testCreateIp() {
        when(ipRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(ipRepository.findById(eq(TEST_IP_ID))).thenReturn(Optional.empty());
        when(ipwhoClient.getIpInfo(eq(TEST_IP))).thenThrow(new RuntimeException());
        when(ipapiClient.getIpInfo(eq(TEST_IP))).thenReturn(IpapiResponseDto.builder()
                        .country("United States of America")
                        .countryCode("US")
                .build());
        when(ipMapper.toDto(any())).thenAnswer(invocation -> {
            Ip result = invocation.getArgument(0);
            assertEquals(TEST_IP_ID, result.getId());
            assertEquals(TEST_IP, result.getIp());
            assertEquals("US", result.getCountryCode());
            assertEquals("United States of America", result.getCountryName());
            assertEquals(1, result.getHistory().size());
            return new IpDto();
        });
        ipService.getIp(TEST_IP, false);
        verify(ipMapper).toDto(any());
    }

    @Test
    public void testUpdateIp() {
        Ip existingIp = Ip.builder()
                .id(TEST_IP_ID)
                .ip(TEST_IP)
                .countryCode("QQ")
                .countryName("Some old name")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.MIN)
                .history(new ArrayList<>())
                .build();
        existingIp.getHistory().add(new RequestHistory());
        when(ipRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(ipRepository.findById(eq(TEST_IP_ID))).thenReturn(Optional.of(existingIp));
        when(ipwhoClient.getIpInfo(eq(TEST_IP))).thenReturn(IpwhoResponseDto.builder()
                .country("United States of America")
                .countryCode("US")
                .build());
        when(ipMapper.toDto(any())).thenAnswer(invocation -> {
            Ip result = invocation.getArgument(0);
            assertEquals(TEST_IP_ID, result.getId());
            assertEquals(TEST_IP, result.getIp());
            assertEquals("US", result.getCountryCode());
            assertEquals("United States of America", result.getCountryName());
            assertEquals(2, result.getHistory().size());
            return new IpDto();
        });
        ipService.getIp(TEST_IP, false);
        verify(ipMapper).toDto(any());
    }

    @Test
    public void testNotUpdatedDueToTime() {
        Ip existingIp = Ip.builder()
                .id(TEST_IP_ID)
                .ip(TEST_IP)
                .countryCode("QQ")
                .countryName("Some old name")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .history(new ArrayList<>())
                .build();
        existingIp.getHistory().add(new RequestHistory());
        when(ipRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(ipRepository.findById(eq(TEST_IP_ID))).thenReturn(Optional.of(existingIp));
        when(ipMapper.toDto(any())).thenAnswer(invocation -> {
            Ip result = invocation.getArgument(0);
            assertEquals(TEST_IP_ID, result.getId());
            assertEquals(TEST_IP, result.getIp());
            assertEquals("QQ", result.getCountryCode());
            assertEquals("Some old name", result.getCountryName());
            assertEquals(1, result.getHistory().size());
            return new IpDto();
        });
        ipService.getIp(TEST_IP, false);
        verify(ipMapper).toDto(any());
    }

    @Test
    public void testIncorrectIp() {
        assertThrows(IncorrectIpException.class, () -> ipService.getIp("8.8.8.", false));
        assertThrows(IncorrectIpException.class, () -> ipService.getIp("", false));
        assertThrows(IncorrectIpException.class, () -> ipService.getIp(null, false));
    }
}
