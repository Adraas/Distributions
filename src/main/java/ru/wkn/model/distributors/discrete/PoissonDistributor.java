package ru.wkn.model.distributors.discrete;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributors.Distributor;

public class PoissonDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, double lambda, double[] probabilities) {
        double alpha = 0;
        double[] distribution = new double[selectionSize];
        for (int index = 0; index < selectionSize; index++) {
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
        return new Distribution(distribution, probabilities);
    }
}
