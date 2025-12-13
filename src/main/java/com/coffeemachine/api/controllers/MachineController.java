package com.coffeemachine.api.controllers;

import com.coffeemachine.api.models.MachineStatus;
import com.coffeemachine.api.services.MachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/machine")
public class MachineController {
    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/status")
    public ResponseEntity<MachineStatus> status() {
        return ResponseEntity.ok(machineService.getStatus());
    }

    // Admin endpoints exist but for unregistered flow we don't need them now
}
