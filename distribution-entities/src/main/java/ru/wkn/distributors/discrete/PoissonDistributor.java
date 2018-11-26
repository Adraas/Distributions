package ru.wkn.distributors.discrete;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

import java.util.Properties;

public class PoissonDistributor extends Distributor {

    @Override
    public Distribution getDistribution(Properties properties) {
        int selectionSize = Integer.parseInt(properties.getProperty("selectionSize"));
        double probability = Double.parseDouble(properties.getProperty("probability"));
        double lambda = Double.parseDouble(properties.getProperty("lambda"));

        double alpha;
        double[] distribution = new double[selectionSize];
        for (int index = 0; index < selectionSize; index++) {
            double exp = Math.exp(-lambda);
            double temp = exp;
            int currentValueOfDistribution = 0;
            alpha = getRandomValue();
            while (temp < alpha) {
                currentValueOfDistribution++;
                exp *= lambda / currentValueOfDistribution;
                temp += exp;
            }
            distribution[index] = currentValueOfDistribution;
        }
        return new Distribution(distribution, probability);
    }

    @Override
    public double[] theoreticalProbabilities() {
        throw new UnsupportedOperationException();
    }
}
