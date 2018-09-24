package ru.wkn.distributions;

import java.io.Writer;
import java.util.Properties;

public abstract class Distribution implements IDistribution {

    private Properties properties;
    private Writer writer;

    public Distribution(Properties properties, Writer writer) {
        this.properties = properties;
        this.writer = writer;
    }

    protected double getRandomValue(double alpha) {
        double random;
        do {
            random = Math.random();
        } while (random == alpha);
        return random;
    }
}
