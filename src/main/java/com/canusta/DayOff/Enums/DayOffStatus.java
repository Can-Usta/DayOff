package com.canusta.DayOff.Enums;

public enum DayOffStatus {
    ONAY("Onaylandı"),
    BEKLEME("Cevap Bekleniyor"),
    RED("Reddedildi");
    private final String description;

    DayOffStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
