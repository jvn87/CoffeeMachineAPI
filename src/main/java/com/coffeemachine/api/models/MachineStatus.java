package com.coffeemachine.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "machine_status")
public class MachineStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer waterMl;
    private Integer cupsMade;
    private boolean maintenanceRequired;

    public MachineStatus() {}

    public MachineStatus(Integer waterMl, Integer cupsMade, boolean maintenanceRequired) {
        this.waterMl = waterMl;
        this.cupsMade = cupsMade;
        this.maintenanceRequired = maintenanceRequired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(Integer waterMl) {
        this.waterMl = waterMl;
    }

    public Integer getCupsMade() {
        return cupsMade;
    }

    public void setCupsMade(Integer cupsMade) {
        this.cupsMade = cupsMade;
    }

    public boolean isMaintenanceRequired() {
        return maintenanceRequired;
    }

    public void setMaintenanceRequired(boolean maintenanceRequired) {
        this.maintenanceRequired = maintenanceRequired;
    }
}
