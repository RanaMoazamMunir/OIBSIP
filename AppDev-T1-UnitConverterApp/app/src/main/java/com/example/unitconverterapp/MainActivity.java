package com.example.unitconverterapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.unitconverterapp.model.ConversionCategory;
import com.example.unitconverterapp.model.Unit;
import com.example.unitconverterapp.converter.UnitConverter;
import com.example.unitconverterapp.converter.ConverterFactory;
import com.example.unitconverterapp.utils.InputValidator;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {

    private EditText inputValue;

    private Spinner categorySpinner;
    private Spinner fromSpinner;
    private Spinner toSpinner;

    private Button convertButton;

    private TextView resultText;
    private UnitConverter currentConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        categorySpinner = findViewById(R.id.categorySpinner);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        ConversionCategory[] categories = ConversionCategory.values();

        ArrayAdapter<ConversionCategory> categoryAdapter =
                new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_spinner_item,
                        categories
                );

        categoryAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ConversionCategory selectedCategory = (ConversionCategory) parent.getItemAtPosition(position);
                        currentConverter = ConverterFactory.getConverter(selectedCategory);
                        updateUnitSpinners();
                        resultText.setText(R.string.result_placeholder);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performConversion();
            }
        });
    }
    private void updateUnitSpinners(){
        ArrayList<Unit> units = currentConverter.getUnits();

        ArrayAdapter<Unit> unitAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item,
                units);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(unitAdapter);
        toSpinner.setAdapter(unitAdapter);

        fromSpinner.setSelection(0);
        if(units.size() > 1){
            toSpinner.setSelection(1);
        }else {
            toSpinner.setSelection(0);
        }
    }
    private void performConversion(){
        String input = inputValue.getText().toString().trim();
        if(InputValidator.isEmpty(input)){
            Toast.makeText(
                    MainActivity.this, "Please enter a Value", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!InputValidator.isNumeric(input)){

            Toast.makeText(MainActivity.this, "Please Enter a valid Number",Toast.LENGTH_SHORT).show();

            return;
        }
        double value = Double.parseDouble(input);

        Unit fromUnit = (Unit) fromSpinner.getSelectedItem();

        Unit toUnit = (Unit) toSpinner.getSelectedItem();

        double result = currentConverter.convert(value,fromUnit,toUnit);
        String resultMessage =
                formatNumber(value)
                + " "
                +fromUnit.getSymbol()
                +"="
                +formatNumber(result)
                +" "
                + toUnit.getSymbol();
        resultText.setText(resultMessage);
    }
    private String formatNumber(double number){
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        return decimalFormat.format(number);
    }
}