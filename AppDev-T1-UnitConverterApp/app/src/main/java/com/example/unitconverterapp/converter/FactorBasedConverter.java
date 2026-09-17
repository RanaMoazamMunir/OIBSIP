package com.example.unitconverterapp.converter;

import com.example.unitconverterapp.model.Unit;

public abstract class FactorBasedConverter extends UnitConverter {

    @Override
    public double convert(
            double value,
            Unit fromUnit,
            Unit toUnit) {

        double baseValue =
                value * fromUnit.getConversionFactor();

        return baseValue / toUnit.getConversionFactor();
    }
}