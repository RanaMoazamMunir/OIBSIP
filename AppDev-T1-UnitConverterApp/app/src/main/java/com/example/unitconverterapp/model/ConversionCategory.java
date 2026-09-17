package com.example.unitconverterapp.model;

public enum ConversionCategory {
    LENGTH("Length"),
    WEIGHT("Weight"),
    TEMPERATURE("Temperature"),

    VOLUME("Volume");

    private final String displayName;
    ConversionCategory(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
