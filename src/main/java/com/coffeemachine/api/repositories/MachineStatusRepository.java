package com.coffeemachine.api.repositories;

import com.coffeemachine.api.models.MachineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MachineStatusRepository extends JpaRepository<MachineStatus, Long> {
    Optional<MachineStatus> findFirstByOrderByIdAsc();
}
