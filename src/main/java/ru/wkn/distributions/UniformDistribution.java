package ru.wkn.distributions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

public class UniformDistribution extends Distribution {

    private Properties properties;

    public UniformDistribution(Properties properties) {
        this.properties = properties;
    }

    @Override
    public double[] getDistribution() {
        double currentValueOfDistribution = 0;
        int selectionSize = Integer.parseInt(properties.getProperty("selectionSize"));
        double[] distribution = new double[selectionSize];
        double a = Double.parseDouble(properties.getProperty("a"));
        double b = Double.parseDouble(properties.getProperty("b"));
        for (int index = 0; index < selectionSize; index++) {
            currentValueOfDistribution = super.getRandomValue(currentValueOfDistribution);
            currentValueOfDistribution = currentValueOfDistribution * a + b;
            currentValueOfDistribution = new BigDecimal(currentValueOfDistribution).setScale(5, RoundingMode.UP).doubleValue();
            distribution[index] = currentValueOfDistribution;
        }
        return distribution;
    }
}
