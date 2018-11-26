package ru.wkn.distributors.discrete;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

public class PoissonDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, double lambda, double probability) {
        double alpha = 0;
        double[] distribution = new double[selectionSize];
        for (int index = 0; index < selectionSize; index++) {
            double exp = Math.exp(-lambda);
            double temp = exp;
            int currentValueOfDistribution = 0;
            alpha = super.getRandomValue();
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
