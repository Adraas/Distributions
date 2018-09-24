package ru.wkn.distributions;

import java.util.Properties;

public class PoissonDistribution extends Distribution {

    private Properties properties;

    public PoissonDistribution(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void distribute() {
        StringBuilder data = new StringBuilder();
        double alpha = 0;
        int size = Integer.parseInt(properties.getProperty("size"));
        for (int i = 0; i < size; i++) {
            double lambda = Double.parseDouble(properties.getProperty("lambda"));
            double exp = Math.exp(-lambda);
            double s = exp;
            int k = 0;
            alpha = super.getRandomValue(alpha);
            while (s < alpha) {
                k++;
                exp *= lambda / k;
                s += exp;
            }
            data.append(String.valueOf(k)).append("\r\n");
        }
    }
}
