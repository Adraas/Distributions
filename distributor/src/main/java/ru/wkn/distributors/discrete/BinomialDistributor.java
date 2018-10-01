package ru.wkn.distributors.discrete;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;
import ru.wkn.utils.QualityControl;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int inputParameter, double[] probabilities) {
        double[] distribution = new double[selectionSize];
        double attitudeOfSuccessToFailure;
        double currentAccumulatedProbability;
        double currentProbability;
        int frequency = 0;
        for (int index = 0; index < selectionSize; index++) {
            attitudeOfSuccessToFailure = probabilities[index] / (1 - probabilities[index]);
            currentAccumulatedProbability = (1 - probabilities[index]) * selectionSize;
            currentProbability = currentAccumulatedProbability;
            while (QualityControl.getSignificanceLevel() > currentAccumulatedProbability) {
                frequency++;
                currentProbability *= attitudeOfSuccessToFailure * (inputParameter - frequency + 1) / frequency;
                currentAccumulatedProbability += currentProbability;
            }
            distribution[index] = frequency;
        }
        return new Distribution(distribution, probabilities, inputParameter);
    }
}
