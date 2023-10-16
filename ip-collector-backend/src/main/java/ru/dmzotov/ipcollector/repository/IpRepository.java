package ru.dmzotov.ipcollector.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.dmzotov.ipcollector.entity.Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<Ip, Long>, JpaSpecificationExecutor<Ip> {
}
