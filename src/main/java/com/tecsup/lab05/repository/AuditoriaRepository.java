package com.tecsup.lab05.repository;

import com.tecsup.lab05.model.AuditoriaLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaRepository extends JpaRepository<AuditoriaLog, Long> {
}