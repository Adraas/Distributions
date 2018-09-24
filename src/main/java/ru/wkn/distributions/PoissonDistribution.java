package ru.wkn.distributions;

import java.util.Properties;

public class PoissonDistribution extends Distribution {

    private Properties properties;

    public PoissonDistribution(Properties properties) {
        this.properties = properties;
    }

    @Override
    public double[] getDistribution() {
        double alpha = 0;
        int selectionSize = Integer.parseInt(properties.getProperty("selectionSize"));
        double[] distribution = new double[selectionSize];
        for (int index = 0; index < selectionSize; index++) {
            double lambda = Double.parseDouble(properties.getProperty("lambda"));
            double exp = Math.exp(-lambda);
            double temp = exp;
            int currentValueOfDistribution = 0;
            alpha = super.getRandomValue(alpha);
            while (temp < alpha) {
                currentValueOfDistribution++;
                exp *= lambda / currentValueOfDistribution;
                temp += exp;
            }
            distribution[index] = currentValueOfDistribution;
        }
        return distribution;
    }
}
