# OIBSIP
# Unit Converter Android App

A simple, clean Android app built with Java and XML for converting values between common measurement units. It's structured around solid OOP principles, so adding new categories or units later is straightforward rather than a headache.

The app updates the available units automatically based on the category you pick, checks your input before doing any math, and shows results with the correct unit symbols attached.

## What it does

- Converts between all the common measurement units
- Handles multiple conversion categories in one place
- Updates the "From" and "To" dropdowns automatically when you switch categories
- Validates input and won't let empty or invalid values through
- Runs accurate conversions and formats the output cleanly
- Shows a Toast message if something's off with your input
- Built with reusable, modular converter classes instead of one giant if-else block

## Supported categories

**Length**
Meter, Centimeter, Kilometer, Inch, Foot

**Weight**
Kilogram, Gram, Pound

**Temperature**
Celsius, Fahrenheit, Kelvin

**Volume**
Liter, Milliliter, Gallon

## Built with

- Java
- XML
- Android Studio
- Android SDK
- Gradle
- Git / GitHub

## Project structure

The codebase separates concerns cleanly: UI logic, data models, the actual conversion math, and input validation all live in their own spaces instead of getting tangled together in one Activity.

```
com.example.unitconverterapp
│
├── MainActivity.java
│
├── model
│   ├── Unit.java
│   └── ConversionCategory.java
│
├── converter
│   ├── UnitConverter.java
│   ├── FactorBasedConverter.java
│   ├── LengthConverter.java
│   ├── WeightConverter.java
│   ├── TemperatureConverter.java
│   ├── VolumeConverter.java
│   └── ConverterFactory.java
│
└── utils
    └── InputValidator.java
```

`UnitConverter` defines the contract every converter follows, while `FactorBasedConverter` handles the shared logic for categories that convert through a simple multiplication factor (Length, Weight, Volume). Temperature gets its own converter since it needs actual formulas instead of a flat factor. `ConverterFactory` decides which converter to hand back based on the selected category, so `MainActivity` never has to know the details — it just asks for a converter and uses it.

## How it works

1. Pick a category (Length, Weight, Temperature, or Volume)
2. The From/To unit lists update automatically for that category
3. Enter a value and hit convert
4. `InputValidator` checks the input — if it's empty or not a valid number, you'll get a Toast telling you so
5. `ConverterFactory` grabs the right converter for the category
6. The result shows up formatted, with the correct unit symbol
