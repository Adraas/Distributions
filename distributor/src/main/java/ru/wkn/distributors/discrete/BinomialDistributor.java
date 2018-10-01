package ru.wkn.distributors.discrete;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

import java.util.Random;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int inputParameter, double[] probabilities) {
        double[] distribution = new double[selectionSize];
        double attitudeOfSuccessToFailure;
        double currentAccumulatedProbability;
        double currentProbability;
        int frequency = 0;
        Random random = new Random();
        for (int index = 0; index < selectionSize; index++) {
            attitudeOfSuccessToFailure = probabilities[index] / (1 - probabilities[index]);
            currentAccumulatedProbability = (1 - probabilities[index]) * selectionSize;
            currentProbability = currentAccumulatedProbability;
            double randomValue = random.nextDouble();
            while (randomValue > currentAccumulatedProbability) {
                frequency++;
                currentProbability *= attitudeOfSuccessToFailure * (inputParameter - frequency + 1) / frequency;
                currentAccumulatedProbability += currentProbability;
            }
            distribution[index] = frequency;
        }
        return new Distribution(distribution);
    }
}
