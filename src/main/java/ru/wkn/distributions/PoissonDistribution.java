package ru.wkn.distributions;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class PoissonDistribution {

    private Properties properties;
    private Writer writer;

    public PoissonDistribution(Properties properties, Writer writer) {
        this.properties = properties;
        this.writer = writer;
    }

    private double getRandomValue(double alpha) {
        double random;
        do {
            random = Math.random();
        } while (random == alpha);
        return random;
    }

    public void distribute() throws IOException {
        double alpha = 0;
        for (int i = 0; i < 100; i++) {
            double lambda = Double.parseDouble(properties.getProperty("lambda"));
            double exp = Math.exp(-lambda);
            double s = exp;
            int k = 0;
            alpha = getRandomValue(alpha);
            while (s < alpha) {
                k++;
                exp *= lambda / k;
                s += exp;
            }
            writer.write(String.valueOf(k) + System.lineSeparator());
            writer.flush();
        }
    }
}
