package com.example.unitconverterapp.converter;

import com.example.unitconverterapp.model.Unit;

public class WeightConverter extends FactorBasedConverter{
    public WeightConverter(){
        units.add(new Unit("Kilogram","kg",1.0));
        units.add(new Unit("Gram", "g", 0.001));
        units.add(new Unit("Pound", "lb", 0.45359237));
    }
}
