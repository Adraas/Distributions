package ru.wkn.distributions;

import java.util.Properties;
import java.util.Random;

public class BinaryDistribution extends Distribution {

    private Properties properties;

    public BinaryDistribution(Properties properties) {
        this.properties = properties;
    }

    @Override
    public double[] getDistribution() {
        double parameter = Double.parseDouble(properties.getProperty("parameter"));
        int selectionSize = Integer.parseInt(properties.getProperty("selectionSize"));
        int n = Integer.parseInt(properties.getProperty("n"));
        double[] distribution = new double[n];
        for (int index = 0; index < selectionSize; index++) {
            double currentValueOfDistribution = 0;
            int temp = 0;
            while (temp < n) {
                currentValueOfDistribution += randomBinaryValue(parameter);
                temp++;
            }
            distribution[index] = currentValueOfDistribution;
        }
        return distribution;
    }

    private double randomBinaryValue(double parameter) {
        double delta, random;
        delta = 1 - parameter;
        random = new Random().nextDouble();
        if (random > delta) {
            return 1;
        }
        return 0;
    }
}
