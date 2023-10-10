package ru.dmzotov.ipcollector.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ru.dmzotov.ipcollector.dto.enums.IpSource;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity
public class RequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ip_id")
    private Long ipId;

    @Column(name = "source_data")
    private String sourceData;

    @Column(name = "source_system")
    @Enumerated(EnumType.STRING)
    private IpSource sourceSystem;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;
}
