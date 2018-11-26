package ru.wkn.distributors.continuous;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

public class ExponentialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, double lambda) {
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
