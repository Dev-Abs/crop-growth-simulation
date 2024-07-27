package simulation;

import java.io.File;
import plant.Plant;
import weather.Weather;
import soil.SoilWater;

public class Simulation {
    private Plant plant = new Plant();
    private Weather weather = new Weather();
    private SoilWater soilWater = new SoilWater();

    public void initialization(int count) {
        if (count == 0) {
            weather.initialization(new File("resources/input_files/WEATHER_IN.txt"));
        }
        if (count == 1) {
            plant.initialization(new File("resources/input_files/PLANT_IN.txt"));
        }
        if (count == 2) {
            soilWater.initialization(new File("resources/input_files/SOIL_IN.txt"));
        }
    }

    public void rateCalculation(int count) {
        if (count == 0) {
            weather.rateCalculation();
        }
        if (count == 1) {
            plant.rateCalculation();
        }
        if (count == 2) {
            soilWater.rateCalculation();
        }
    }

    public void integration(int count) {
        if (count == 1) {
            plant.integration();
        }
        if (count == 2) {
            soilWater.integration();
        }
    }

    public void output() {
        plant.output();
        soilWater.output();
    }

    public void close() {
        plant.close();
        weather.close();
        soilWater.close();
    }
}
