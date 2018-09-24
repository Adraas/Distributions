package ru.wkn.distributions;

import java.util.Properties;

public abstract class Distribution implements IDistribution {

    private Properties properties;

    public Distribution(Properties properties) {
        this.properties = properties;
    }

    protected double getRandomValue(double alpha) {
        double random;
        do {
            random = Math.random();
        } while (random == alpha);
        return random;
    }
}
