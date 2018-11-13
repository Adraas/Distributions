package ru.wkn.distributors.discrete;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int valueRange, double probability) {
        double[] implementationsOfRandomVariables = new double[selectionSize];
        int currentImplementationOfRandomVariable;
        double randomValue;

        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            currentImplementationOfRandomVariable = 0;

            for (int i = 0; i < valueRange; i++) {
                randomValue = super.getRandomValue();
                if (randomValue < probability) {
                    currentImplementationOfRandomVariable++;
                }
            }
            implementationsOfRandomVariables[indexOfProbability] = currentImplementationOfRandomVariable;
        }
        return new Distribution(implementationsOfRandomVariables, probability);
    }
}
