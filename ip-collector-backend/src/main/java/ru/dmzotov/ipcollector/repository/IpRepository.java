package ru.dmzotov.ipcollector.repository;

import ru.dmzotov.ipcollector.model.Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<Ip, Long> {
}