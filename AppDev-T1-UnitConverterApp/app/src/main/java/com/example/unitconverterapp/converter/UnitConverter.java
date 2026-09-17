package com.example.unitconverterapp.converter;
import com.example.unitconverterapp.model.Unit;
import java.util.ArrayList;
public abstract class UnitConverter {
    protected final ArrayList<Unit> units;
    public UnitConverter(){
        units = new ArrayList<>();
    }

    public ArrayList<Unit> getUnits() {
        return new ArrayList<>(units);
    }
    public abstract double convert(
            double value,
            Unit fromUnit,
            Unit toUnit
    );
}
