# Crop Growth Simulation

This project simulates crop growth, incorporating factors like plant parameters, weather conditions, and soil moisture levels. It features a simple GUI for input and result display.

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/Dev-Abs/crop-growth-simulation.git
    ```

2. Open the project in your IDE.

3. Build the project and run the `BankInput` class.

## Project Structure

```
src/
├── GUI/
│   └── BankInput.java
├── simulation/
│   └── Simulation.java
├── plant/
│   └── Plant.java
├── weather/
│   └── Weather.java
└── soil/
     └── SoilWater.java

resources/
└── input_files/
     ├── PLANT_IN.txt
     ├── WEATHER_IN.txt
     └── SOIL_IN.txt
```

## Input Files

Located in `resources/input_files/`:

- `PLANT_IN.txt`: Plant parameters.
- `WEATHER_IN.txt`: Weather parameters.
- `SOIL_IN.txt`: Soil moisture parameters.

## Running the Simulation

1. Run the `BankInput` class.
2. Input the required data.
3. View the results in the console and output file.

## License

This project is licensed under the MIT License.