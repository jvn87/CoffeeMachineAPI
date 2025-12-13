package com.coffeemachine.api.services;

import com.coffeemachine.api.models.MachineStatus;
import com.coffeemachine.api.repositories.MachineStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MachineService {
    private final MachineStatusRepository repo;
    private static final int WATER_PER_CUP_ML = 80;

    public MachineService(MachineStatusRepository repo) {
        this.repo = repo;
    }

    public MachineStatus getStatus() {
        return repo.findFirstByOrderByIdAsc()
                .orElse(new MachineStatus(1000, 0, false));
    }

    @Transactional
    public void consumeWaterForCup() {
        MachineStatus s = getStatus();
        int newWater = s.getWaterMl() - WATER_PER_CUP_ML;
        s.setWaterMl(Math.max(newWater, 0));
        s.setCupsMade(s.getCupsMade() + 1);
        if (s.getWaterMl() <= 0) {
            s.setMaintenanceRequired(true);
        }
        repo.save(s);
    }

    @Transactional
    public MachineStatus refillWater(int ml) {
        MachineStatus s = getStatus();
        s.setWaterMl(s.getWaterMl() + ml);
        s.setMaintenanceRequired(false);
        return repo.save(s);
    }

    @Transactional
    public MachineStatus resetMaintenance() {
        MachineStatus s = getStatus();
        s.setMaintenanceRequired(false);
        return repo.save(s);
    }
}
