package ru.wkn.model.distributors.discrete;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributors.Distributor;

import java.util.Random;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int inputParameter, double[] probabilities) {
        if (probabilities == null) {
            probabilities = probabilitiesByDefault(selectionSize);
        }
        double[] implementationOfRandomVariables = new double[selectionSize];
        double attitudeOfSuccessToFailure;
        double currentAccumulatedProbability;
        double currentProbability;
        int currentImplementation;
        double randomValue = 0;
        for (int index = 0; index < selectionSize; index++) {
            currentImplementation = 0;
            attitudeOfSuccessToFailure = probabilities[index] / (1 - probabilities[index]);
            currentAccumulatedProbability = Math.pow(1 - probabilities[index], inputParameter);
            currentProbability = currentAccumulatedProbability;
            randomValue = super.getRandomValue(randomValue);
            while (randomValue > currentAccumulatedProbability) {
                currentImplementation++;
                currentProbability *= attitudeOfSuccessToFailure * (inputParameter - currentImplementation + 1) / currentImplementation;
                currentAccumulatedProbability += currentProbability;
            }
            implementationOfRandomVariables[index] = currentImplementation;
        }
        return new Distribution(implementationOfRandomVariables, probabilities);
    }

    private double[] probabilitiesByDefault(int selectionSize) {
        double[] probabilities = new double[selectionSize];
        Random random = new Random();
        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            probabilities[indexOfProbability] = random.nextDouble();
        }
        return probabilities;
    }
}
