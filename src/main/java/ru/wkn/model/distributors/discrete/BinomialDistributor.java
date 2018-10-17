package ru.wkn.model.distributors.discrete;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;
import ru.wkn.model.distributors.Distributor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int valueRange, double[] probabilities) {
        if (probabilities == null) {
            probabilities = probabilitiesByDefault(selectionSize);
        }
        double[] implementationOfRandomVariables = new double[selectionSize];
        double attitudeOfSuccessToFailure;
        double currentAccumulatedProbability;
        double currentProbability;
        int currentImplementationOfRandomVariable;
        double randomValue = 0;
        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            currentImplementationOfRandomVariable = 0;
            attitudeOfSuccessToFailure = probabilities[indexOfProbability] / (1 - probabilities[indexOfProbability]);
            currentAccumulatedProbability = Math.pow(1 - probabilities[indexOfProbability], valueRange);
            currentProbability = currentAccumulatedProbability;
            randomValue = super.getRandomValue(randomValue);
            while (randomValue > currentAccumulatedProbability) {
                currentImplementationOfRandomVariable++;
                currentProbability *= attitudeOfSuccessToFailure
                        * (valueRange - currentImplementationOfRandomVariable + 1)
                        / currentImplementationOfRandomVariable;
                currentAccumulatedProbability += currentProbability;
            }
            implementationOfRandomVariables[indexOfProbability] = currentImplementationOfRandomVariable;
        }
        return new Distribution(implementationOfRandomVariables, probabilities);
    }

    public Interval[] intervalsOfDistribution(Distribution distribution, int quantityOfIntervals) {
        Interval[] intervals = new Interval[quantityOfIntervals];
        double[] implementationsOfRandomVariable = distribution.getImplementationsOfRandomVariable();
        Arrays.sort(implementationsOfRandomVariable);
        double maxValueOfImplementationOfRandomVariable
                = implementationsOfRandomVariable[implementationsOfRandomVariable.length - 1];
        double minValueOfImplementationOfRandomVariable = implementationsOfRandomVariable[0];
        int increment = (int) ((maxValueOfImplementationOfRandomVariable - minValueOfImplementationOfRandomVariable)
                / quantityOfIntervals);
        int indexOfCurrentPartOfImplementationOfRandomVariable = 0;
        for (int indexOfInterval = 0; indexOfInterval < intervals.length; indexOfInterval++) {
            List<Double> currentPartOfImplementationsOfRandomVariableAsList = new ArrayList<>();
            while (implementationsOfRandomVariable[indexOfCurrentPartOfImplementationOfRandomVariable]
                    < increment * (indexOfInterval + 1)) {
                currentPartOfImplementationsOfRandomVariableAsList
                        .add(implementationsOfRandomVariable[indexOfCurrentPartOfImplementationOfRandomVariable]);
                indexOfCurrentPartOfImplementationOfRandomVariable++;
            }
            double[] currentPartOfImplementationsOfRandomVariable
                    = convertListToArray(currentPartOfImplementationsOfRandomVariableAsList);
            Distribution currentPartOfDistribution
                    = new Distribution(currentPartOfImplementationsOfRandomVariable, distribution.getProbabilities());
            intervals[indexOfInterval]
                    = new Interval(indexOfInterval, indexOfInterval + increment, currentPartOfDistribution);
        }
        return intervals;
    }

    private double[] convertListToArray(List<Double> list) {
        int size = list.size();
        double[] doubles = new double[size];
        for (int index = 0; index < size; index++) {
            doubles[index] = list.get(index);
        }
        return doubles;
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
