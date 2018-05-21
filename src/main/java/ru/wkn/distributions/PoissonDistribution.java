package ru.wkn.distributions;

import java.io.BufferedWriter;
import java.io.IOException;

public class PoissonDistribution {

    private BufferedWriter bufferedWriter;

    public PoissonDistribution(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
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
            bufferedWriter.write(k + "\n");
        }
    }
}
