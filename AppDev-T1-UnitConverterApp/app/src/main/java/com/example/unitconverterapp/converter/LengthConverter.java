package com.example.unitconverterapp.converter;

import com.example.unitconverterapp.model.Unit;

public class LengthConverter extends FactorBasedConverter {

    public LengthConverter() {

        units.add(new Unit("Meter", "m", 1.0));
        units.add(new Unit("Centimeter", "cm", 0.01));
        units.add(new Unit("Kilometer", "km", 1000.0));
        units.add(new Unit("Inch", "in", 0.0254));
        units.add(new Unit("Foot", "ft", 0.3048));
    }
}