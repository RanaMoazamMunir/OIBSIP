package com.example.unitconverterapp.converter;
import com.example.unitconverterapp.model.Unit;
public class VolumeConverter extends FactorBasedConverter{
    public VolumeConverter(){
        units.add(new Unit("Liter","L", 1.0));
        units.add(new Unit("Milliliter","mL", 0.001));
        units.add(new Unit("Gallon","gal", 3.78541));
    }
}
