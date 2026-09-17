package com.example.unitconverterapp.converter;

import com.example.unitconverterapp.model.Unit;
public class TemperatureConverter extends UnitConverter {
    public TemperatureConverter() {
        units.add(new Unit("Celsius", "C", 1.0));
        units.add(new Unit("Fahrenheit", "F", 1.0));
        units.add(new Unit("Kelvin", "K", 1.0));
    }

    @Override
    public double convert(
            double value,
            Unit fromUnit,
            Unit toUnit) {

        double celsiusValue;

        if (fromUnit.getName().equals("Celsius")) {
            celsiusValue = value;
        } else if (fromUnit.getName().equals("Fahrenheit")) {
            celsiusValue = (value - 32) * 5 / 9;
        } else {
            celsiusValue = value - 273.15;
        }
        if (toUnit.getName().equals("Celsius")) {
            return celsiusValue;
        } else if (toUnit.getName().equals("Fahrenheit")) {
            return (celsiusValue * 9 / 5) + 32;
        } else {
            return celsiusValue + 273.15;
        }
    }
}