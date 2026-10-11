
package com.example.calculator.engine;

import com.example.calculator.evaluator.BasicExpressionEvaluator;
import com.example.calculator.evaluator.ExpressionEvaluator;
import com.example.calculator.model.CalculationResult;

public class CalculatorEngine {

    private final StringBuilder expression;
    private final ExpressionEvaluator evaluator;

    private boolean resultShown;
    private String lastResult;

    public CalculatorEngine() {
        this(new BasicExpressionEvaluator());
    }

    public CalculatorEngine(ExpressionEvaluator evaluator) {
        this.expression = new StringBuilder();
        this.evaluator = evaluator;
        this.resultShown = false;
        this.lastResult = "";
    }

    public String getExpressionForDisplay() {

        if (expression.length() == 0) {
            return "0";
        }

        return expression.toString()
                .replace('*', '×')
                .replace('/', '÷')
                .replace('-', '−');
    }

    public void appendDigit(char digit) {

        if (digit < '0' || digit > '9') {
            return;
        }

        if (resultShown) {
            expression.setLength(0);
            resultShown = false;
        }

        if (expression.toString().equals("0")) {
            expression.setLength(0);
        }

        expression.append(digit);
    }

    public void appendOperator(char operator) {

        if (!isOperator(operator)) {
            return;
        }

        if (resultShown) {
            expression.setLength(0);
            expression.append(lastResult);
            resultShown = false;
        }

        if (expression.length() == 0) {

            if (operator == '-') {
                expression.append(operator);
            }

            return;
        }

        int lastIndex = expression.length() - 1;
        char last = expression.charAt(lastIndex);

        if (isOperator(last)) {

            boolean unaryMinus =
                    last == '-' &&
                            (lastIndex == 0 ||
                                    isOperator(expression.charAt(lastIndex - 1)));

            if (unaryMinus) {
                return;
            }

            if (operator == '-') {
                expression.append(operator);
            } else {
                expression.setCharAt(lastIndex, operator);
            }

        } else {
            expression.append(operator);
        }
    }

    public void appendDecimal() {

        if (resultShown) {
            expression.setLength(0);
            resultShown = false;
        }

        if (expression.length() == 0) {
            expression.append("0.");
            return;
        }

        char last = expression.charAt(expression.length() - 1);

        if (isOperator(last)) {
            expression.append("0.");
            return;
        }

        for (int i = expression.length() - 1; i >= 0; i--) {

            char current = expression.charAt(i);

            if (current == '.') {
                return;
            }

            if (current == '+' ||
                    current == '*' ||
                    current == '/') {
                break;
            }

            if (current == '-' &&
                    i > 0 &&
                    !isOperator(expression.charAt(i - 1))) {
                break;
            }
        }

        expression.append('.');
    }

    public void deleteLast() {

        if (resultShown) {
            expression.setLength(0);
            expression.append(lastResult);
            resultShown = false;
        }

        if (expression.length() > 0) {
            expression.deleteCharAt(expression.length() - 1);
        }
    }

    public void clear() {

        expression.setLength(0);
        resultShown = false;
        lastResult = "";
    }

    public CalculationResult evaluate() {

        if (expression.length() == 0) {
            return CalculationResult.error(
                    "Enter a calculation"
            );
        }

        CalculationResult result =
                evaluator.evaluate(expression.toString());

        if (result.isSuccess()) {
            lastResult = formatNumber(result.getValue());
            resultShown = true;
        }

        return result;
    }

    private boolean isOperator(char operator) {

        return operator == '+' ||
                operator == '-' ||
                operator == '*' ||
                operator == '/';
    }

    private String formatNumber(double value) {

        if (value == 0.0) {
            return "0";
        }

        return java.math.BigDecimal.valueOf(value)
                .stripTrailingZeros()
                .toPlainString();
    }
}
