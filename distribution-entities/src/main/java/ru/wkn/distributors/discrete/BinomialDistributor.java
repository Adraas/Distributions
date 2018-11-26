package ru.wkn.distributors.discrete;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;

import java.math.BigInteger;

public class BinomialDistributor extends Distributor {

    private Distribution distribution;

    public Distribution getDistribution(int selectionSize, int valueRange, double probability) {
        double[] implementationsOfRandomVariables = new double[selectionSize];
        int currentImplementationOfRandomVariable;

        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            currentImplementationOfRandomVariable = 0;

            for (int i = 0; i < valueRange; i++) {
                if (getRandomValue() < probability) {
                    currentImplementationOfRandomVariable++;
                }
            }
            implementationsOfRandomVariables[indexOfProbability] = currentImplementationOfRandomVariable;
        }
        distribution = new Distribution(implementationsOfRandomVariables, probability);
        return distribution;
    }

    public double[] theoreticalProbabilities() {
        int selectionSize = distribution.getRandomSample().length;
        double[] probabilities = new double[selectionSize];
        double probabilityOfFailure = 1 - distribution.getProbability();

        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            probabilities[indexOfProbability] = СombinatorialAnalysis.combinations(selectionSize, indexOfProbability)
                    .longValue()
                    * Math.pow(distribution.getProbability(), distribution.getRandomSample()[indexOfProbability])
                    * Math.pow(probabilityOfFailure, selectionSize - distribution.getRandomSample()[indexOfProbability]);
        }
        return probabilities;
    }

    private static class СombinatorialAnalysis {

        private static BigInteger combinations(int generalValue, int currentValue) {
            return placements(generalValue, currentValue).divide(factorial(BigInteger.valueOf(currentValue)));
        }

        private static BigInteger placements(int generalValue, int currentValue) {
            return factorial(BigInteger.valueOf(generalValue)).divide(factorial(BigInteger.valueOf(generalValue - currentValue)));
        }

        private static BigInteger factorial(BigInteger value) {
            return (value.longValue() > 0) ? factorial(value.subtract(BigInteger.valueOf(1)))
                    .multiply(value) : BigInteger.valueOf(1);
        }
    }
}
