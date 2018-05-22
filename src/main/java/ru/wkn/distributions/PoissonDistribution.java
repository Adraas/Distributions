package ru.wkn.distributions;

import java.io.IOException;
import java.io.Writer;

public class PoissonDistribution {

    private Writer writer;

    public PoissonDistribution(Writer writer) {
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
            double lambda = 0.95;
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
