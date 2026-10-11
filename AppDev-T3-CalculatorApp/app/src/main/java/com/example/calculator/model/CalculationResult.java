package com.example.calculator.model;

public class CalculationResult {

    private final boolean success;
    private final double value;
    private final String message;

    private CalculationResult(
            boolean success,
            double value,
            String message) {

        this.success = success;
        this.value = value;
        this.message = message;
    }

    public static CalculationResult success(double value) {
        return new CalculationResult(
                true,
                value,
                ""
        );
    }

    public static CalculationResult error(String message) {
        return new CalculationResult(
                false,
                0,
                message
        );
    }

    public boolean isSuccess() {
        return success;
    }

    public double getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}