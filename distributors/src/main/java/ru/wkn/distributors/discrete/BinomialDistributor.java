package ru.wkn.distributors.discrete;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

import java.util.Random;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int inputParameter, double[] probabilities) {
        if (probabilities == null) {
            probabilities = probabilitiesByDefault(selectionSize);
        }
        double[] distribution = new double[selectionSize];
        double attitudeOfSuccessToFailure;
        double currentAccumulatedProbability;
        double currentProbability;
        int frequency = 0;
        for (int index = 0; index < selectionSize; index++) {
            attitudeOfSuccessToFailure = probabilities[index] / (1 - probabilities[index]);
            currentAccumulatedProbability = (1 - probabilities[index]) * selectionSize;
            currentProbability = currentAccumulatedProbability;
            double randomValue = 0;
            randomValue = super.getRandomValue(randomValue);
            while (randomValue > currentAccumulatedProbability) {
                frequency++;
                currentProbability *= attitudeOfSuccessToFailure * (inputParameter - frequency + 1) / frequency;
                currentAccumulatedProbability += currentProbability;
            }
            distribution[index] = frequency;
        }
        return new Distribution(distribution, probabilities);
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
