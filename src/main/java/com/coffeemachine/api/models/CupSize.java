package com.coffeemachine.api.models;

public enum CupSize {
    SMALL(100),
    MEDIUM(200),
    LARGE(300);

    private final int milliliters;

    CupSize(int milliliters) {
        this.milliliters = milliliters;
    }

    public int getMilliliters() {
        return milliliters;
    }
}