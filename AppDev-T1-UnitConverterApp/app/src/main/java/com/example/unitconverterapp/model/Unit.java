package com.example.unitconverterapp.model;

import java.security.SecureRandom;

public class Unit {
    private String name;
    private String symbol;
    private double conversionFactor;
    public Unit(String name, String symbol, double conversionFactor){
        this.name = name;
        this.symbol = symbol;
        this.conversionFactor = conversionFactor;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
    public String toString(){
        return name;
    }
}
