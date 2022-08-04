package com.canusta.DayOff.Enums;

public enum UserType {
    PERSONEL("Personel"),
    ADMİN("Admin");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
