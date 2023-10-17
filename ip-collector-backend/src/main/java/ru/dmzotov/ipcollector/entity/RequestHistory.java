package ru.dmzotov.ipcollector.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.dmzotov.ipcollector.dto.enums.IpSource;

import java.time.LocalDateTime;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="request_history_id_seq")
    @SequenceGenerator(name="request_history_id_seq", sequenceName="request_history_id_seq", allocationSize = 1)
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
