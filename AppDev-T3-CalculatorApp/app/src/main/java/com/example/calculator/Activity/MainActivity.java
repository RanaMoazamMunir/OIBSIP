
package com.example.calculator.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;
import com.example.calculator.engine.CalculatorEngine;
import com.example.calculator.model.CalculationResult;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private TextView expressionDisplay;
    private TextView resultDisplay;

    private CalculatorEngine calculatorEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorEngine = new CalculatorEngine();

        expressionDisplay =
                findViewById(R.id.expressionDisplay);

        resultDisplay =
                findViewById(R.id.resultDisplay);

        connectNumberButtons();
        connectOperatorButtons();
        connectActionButtons();

        expressionDisplay.setText("0");
    }

    private void connectNumberButtons() {

        bindDigit(R.id.button0, '0');
        bindDigit(R.id.button1, '1');
        bindDigit(R.id.button2, '2');
        bindDigit(R.id.button3, '3');
        bindDigit(R.id.button4, '4');
        bindDigit(R.id.button5, '5');
        bindDigit(R.id.button6, '6');
        bindDigit(R.id.button7, '7');
        bindDigit(R.id.button8, '8');
        bindDigit(R.id.button9, '9');
    }

    private void bindDigit(int buttonId, char digit) {

        Button button = findViewById(buttonId);

        button.setOnClickListener(view -> {
            calculatorEngine.appendDigit(digit);
            updateExpression();
            resultDisplay.setText("");
        });
    }

    private void connectOperatorButtons() {

        Button addButton = findViewById(R.id.buttonAdd);
        Button subtractButton =
                findViewById(R.id.buttonSubtract);
        Button multiplyButton =
                findViewById(R.id.buttonMultiply);
        Button divideButton =
                findViewById(R.id.buttonDivide);

        addButton.setOnClickListener(view ->
                appendOperator('+'));

        subtractButton.setOnClickListener(view ->
                appendOperator('-'));

        multiplyButton.setOnClickListener(view ->
                appendOperator('*'));

        divideButton.setOnClickListener(view ->
                appendOperator('/'));
    }

    private void appendOperator(char operator) {

        calculatorEngine.appendOperator(operator);
        updateExpression();
        resultDisplay.setText("");
    }

    private void connectActionButtons() {

        Button decimalButton =
                findViewById(R.id.buttonDecimal);

        Button equalsButton =
                findViewById(R.id.buttonEquals);

        Button clearButton =
                findViewById(R.id.buttonClear);

        Button backspaceButton =
                findViewById(R.id.buttonBackspace);

        decimalButton.setOnClickListener(view -> {
            calculatorEngine.appendDecimal();
            updateExpression();
            resultDisplay.setText("");
        });

        equalsButton.setOnClickListener(view ->
                calculateResult());

        clearButton.setOnClickListener(view -> {
            calculatorEngine.clear();
            expressionDisplay.setText("0");
            resultDisplay.setText("");
        });

        backspaceButton.setOnClickListener(view -> {
            calculatorEngine.deleteLast();
            updateExpression();
            resultDisplay.setText("");
        });
    }

    private void calculateResult() {

        CalculationResult result =
                calculatorEngine.evaluate();

        if (result.isSuccess()) {

            String formattedResult =
                    BigDecimal.valueOf(result.getValue())
                            .stripTrailingZeros()
                            .toPlainString();

            resultDisplay.setText(formattedResult);

        } else {

            resultDisplay.setText(result.getMessage());
        }
    }

    private void updateExpression() {

        expressionDisplay.setText(
                calculatorEngine.getExpressionForDisplay()
        );
    }
}
