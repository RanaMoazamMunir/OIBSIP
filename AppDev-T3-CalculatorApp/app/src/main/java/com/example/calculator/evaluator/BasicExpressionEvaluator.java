
package com.example.calculator.evaluator;

import com.example.calculator.model.CalculationResult;

import java.util.ArrayDeque;

public class BasicExpressionEvaluator
        implements ExpressionEvaluator {

    @Override
    public CalculationResult evaluate(String expression) {

        if (expression == null ||
                expression.trim().isEmpty()) {

            return CalculationResult.error(
                    "Enter an expression"
            );
        }

        // Normalize the symbols displayed by the UI.
        String input = expression
                .replace('×', '*')
                .replace('÷', '/')
                .replace('−', '-');

        ArrayDeque<Double> numbers =
                new ArrayDeque<>();

        ArrayDeque<Character> operators =
                new ArrayDeque<>();

        int index = 0;
        boolean expectingNumber = true;

        while (index < input.length()) {

            char current = input.charAt(index);

            if (Character.isWhitespace(current)) {
                index++;
                continue;
            }

            // Read a number, including a leading negative sign.
            if (Character.isDigit(current) ||
                    current == '.' ||
                    (current == '-' && expectingNumber)) {

                int start = index;

                if (current == '-' && expectingNumber) {
                    index++;
                }

                boolean hasDigit = false;
                boolean hasDecimal = false;

                while (index < input.length()) {

                    char c = input.charAt(index);

                    if (Character.isDigit(c)) {

                        hasDigit = true;
                        index++;

                    } else if (c == '.') {

                        if (hasDecimal) {
                            return CalculationResult.error(
                                    "Invalid decimal number"
                            );
                        }

                        hasDecimal = true;
                        index++;

                    } else {
                        break;
                    }
                }

                if (!hasDigit) {
                    return CalculationResult.error(
                            "Invalid number"
                    );
                }

                try {

                    double number = Double.parseDouble(
                            input.substring(start, index)
                    );

                    numbers.push(number);

                } catch (NumberFormatException exception) {

                    return CalculationResult.error(
                            "Invalid number"
                    );
                }

                expectingNumber = false;
                continue;
            }

            if (isOperator(current)) {

                if (expectingNumber) {
                    return CalculationResult.error(
                            "Invalid expression"
                    );
                }

                // Evaluate operators of equal or higher precedence first.
                while (!operators.isEmpty() &&
                        precedence(operators.peek()) >=
                                precedence(current)) {

                    CalculationResult result =
                            applyTopOperator(
                                    numbers,
                                    operators.pop()
                            );

                    if (!result.isSuccess()) {
                        return result;
                    }

                    numbers.push(result.getValue());
                }

                operators.push(current);
                expectingNumber = true;
                index++;
                continue;
            }

            return CalculationResult.error(
                    "Invalid character"
            );
        }

        if (expectingNumber) {
            return CalculationResult.error(
                    "Expression cannot end with an operator"
            );
        }

        // Evaluate any operators that remain.
        while (!operators.isEmpty()) {

            CalculationResult result =
                    applyTopOperator(
                            numbers,
                            operators.pop()
                    );

            if (!result.isSuccess()) {
                return result;
            }

            numbers.push(result.getValue());
        }

        if (numbers.size() != 1) {
            return CalculationResult.error(
                    "Invalid expression"
            );
        }

        double answer = numbers.pop();

        if (Double.isNaN(answer) ||
                Double.isInfinite(answer)) {

            return CalculationResult.error(
                    "Result is too large"
            );
        }

        return CalculationResult.success(answer);
    }

    private boolean isOperator(char operator) {

        return operator == '+' ||
                operator == '-' ||
                operator == '*' ||
                operator == '/';
    }

    private int precedence(char operator) {

        if (operator == '*' || operator == '/') {
            return 2;
        }

        return 1;
    }

    private CalculationResult applyTopOperator(
            ArrayDeque<Double> numbers,
            char operator) {

        if (numbers.size() < 2) {
            return CalculationResult.error(
                    "Invalid expression"
            );
        }

        double right = numbers.pop();
        double left = numbers.pop();
        double answer;

        switch (operator) {

            case '+':
                answer = left + right;
                break;

            case '-':
                answer = left - right;
                break;

            case '*':
                answer = left * right;
                break;

            case '/':

                if (right == 0.0) {
                    return CalculationResult.error(
                            "Error: division by zero"
                    );
                }

                answer = left / right;
                break;

            default:
                return CalculationResult.error(
                        "Unknown operator"
                );
        }

        if (Double.isNaN(answer) ||
                Double.isInfinite(answer)) {

            return CalculationResult.error(
                    "Result is too large"
            );
        }

        return CalculationResult.success(answer);
    }
}
