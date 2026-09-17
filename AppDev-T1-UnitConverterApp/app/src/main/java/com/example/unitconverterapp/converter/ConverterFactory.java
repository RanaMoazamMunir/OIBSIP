package com.example.unitconverterapp.converter;

import com.example.unitconverterapp.model.ConversionCategory;

import java.util.HashMap;

public class ConverterFactory {

    private static final HashMap<ConversionCategory, UnitConverter> converters = new HashMap<>();

    static {
        converters.put(
                ConversionCategory.LENGTH,
                new LengthConverter());
        converters.put(
                ConversionCategory.WEIGHT,
                new WeightConverter());
        converters.put(
                ConversionCategory.TEMPERATURE,
                new TemperatureConverter());
        converters.put(
                ConversionCategory.VOLUME,
                new VolumeConverter());
    }

    public static UnitConverter getConverter(ConversionCategory category) {

        UnitConverter converter = converters.get(category);

        if (converter == null) {
            throw new IllegalArgumentException("Unsupported category: " + category);
        }

        return converter;
    }
}