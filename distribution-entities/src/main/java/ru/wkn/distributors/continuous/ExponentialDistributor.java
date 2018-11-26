package ru.wkn.distributors.continuous;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

import java.util.Properties;

public class ExponentialDistributor extends Distributor {

    @Override
    public Distribution getDistribution(Properties properties) {
        int selectionSize = Integer.parseInt(properties.getProperty("selectionSize"));
        double lambda = Double.parseDouble(properties.getProperty("lambda"));

        double[] randomSample = new double[selectionSize];

        for (int i = 0; i < selectionSize; i++) {
            randomSample[i] = - Math.log(getRandomValue()) / lambda;
        }
        return new Distribution(randomSample);
    }

    @Override
    public double[] theoreticalProbabilities() {
        throw new UnsupportedOperationException();
    }
}
