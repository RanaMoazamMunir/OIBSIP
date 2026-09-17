package com.example.unitconverterapp.utils;

public class InputValidator {
    public static boolean isEmpty(String input){
        return input == null || input.trim().isEmpty();
    }

    public static boolean isNumeric(String input){
        try{
            double value = Double.parseDouble(input);
            return !Double.isNaN(value) && !Double.isInfinite(value);
        }catch (NumberFormatException e){
            return false;
        }
    }
}
