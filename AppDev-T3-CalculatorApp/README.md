# Task 3: Calculator App

A clean Android calculator built with Java and XML. It supports basic arithmetic operations and provides input controls and error handling for common calculation cases.

## Features

- Digits 0–9 and decimal input
- Addition, subtraction, multiplication, and division
- Expression and result display
- Clear and backspace controls
- Division-by-zero error handling
- Button-based calculator layout

## Technologies

- Java
- XML layouts
- Android SDK
- Android Studio
- Gradle

## Architecture

The application uses separate components for interface handling and calculation logic:

- MainActivity connects the calculator UI to the engine and responds to button taps.
- CalculatorEngine coordinates expression processing and calculation requests.
- ExpressionEvaluator defines the evaluator contract.
- BasicExpressionEvaluator implements expression evaluation.
- CalculationResult represents the calculation result and error state.

This separation keeps the Activity focused on UI interactions while calculation logic remains in dedicated classes. The evaluator also uses an ArrayDeque to manage values during expression processing.

## Run the App

1. Open the AppDev-T3-CalculatorApp folder in Android Studio.
2. Allow Gradle synchronization to complete.
3. Start an Android emulator or connect a device.
4. Run the app from Android Studio.

This project was created as Task 3 of the OASIS INFOBYTE Android Development internship project set.
