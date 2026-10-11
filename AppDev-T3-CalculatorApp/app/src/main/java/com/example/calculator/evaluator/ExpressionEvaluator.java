package com.example.calculator.evaluator;

import com.example.calculator.model.CalculationResult;

public interface ExpressionEvaluator {

    CalculationResult evaluate(String expression);
}