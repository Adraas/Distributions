package ru.wkn.distributions;

import java.util.Properties;
import java.util.Random;

public abstract class Distribution implements IDistribution {

    private Properties properties;

    public Distribution(Properties properties) {
        this.properties = properties;
    }

    protected double getRandomValue(double alpha) {
        Random random = new Random();
        double randomValue;
        do {
            randomValue = random.nextDouble();
        } while (randomValue == alpha);
        return randomValue;
    }
}
