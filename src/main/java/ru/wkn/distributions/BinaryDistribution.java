package ru.wkn.distributions;

import java.util.Properties;
import java.util.Random;

public class BinaryDistribution extends Distribution {

    private Properties properties;

    public BinaryDistribution(Properties properties) {
        super(properties);
        this.properties = properties;
    }

    @Override
    public void distribute() {
        StringBuilder data = new StringBuilder();
        double parameter = Double.parseDouble(properties.getProperty("parameter"));
        int size = Integer.parseInt(properties.getProperty("size"));
        int n = Integer.parseInt(properties.getProperty("n"));
        for (int i = 0; i < size; i++) {
            double sum = 0;
            int j = 0;
            while (j < n) {
                sum += binary(parameter);
                j++;
            }
            data.append(sum).append("\r\n");
        }
    }

    private double binary(double parameter) {
        double delta, random;
        delta = 1 - parameter;
        random = new Random().nextDouble();
        if (random > delta) {
            return 1;
        }
        return 0;
    }
}
