package ru.wkn.model.distributors.discrete;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;
import ru.wkn.model.distributors.Distributor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int valueRange, double probability) {
        double[] distributionTable = probabilitiesByBinomialLaw(selectionSize, probability);
        double[] implementationOfRandomVariables = new double[selectionSize];
        double attitudeOfSuccessToFailure;
        double currentAccumulatedProbability;
        double currentProbability;
        int currentImplementationOfRandomVariable;
        double randomValue = 0;

        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            currentImplementationOfRandomVariable = 0;
            attitudeOfSuccessToFailure = distributionTable[indexOfProbability]
                    / (1 - distributionTable[indexOfProbability]);
            currentAccumulatedProbability = Math.pow(1 - distributionTable[indexOfProbability], valueRange);
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
        return new Distribution(implementationOfRandomVariables, distributionTable);
    }

    public Interval[] intervalsOfDistribution(Distribution distribution, int quantityOfIntervals) {
        Interval[] intervals = new Interval[quantityOfIntervals];
        Arrays.sort(distribution.getDistributionRecords());

        double[] implementationsOfRandomVariable = distribution.getImplementationsOfRandomVariable();
        double[] distributionTable = distribution.getDistributionTable();
        double maxValueOfImplementationOfRandomVariable
                = implementationsOfRandomVariable[implementationsOfRandomVariable.length - 1];
        double minValueOfImplementationOfRandomVariable = implementationsOfRandomVariable[0];
        int increment = (int) ((maxValueOfImplementationOfRandomVariable - minValueOfImplementationOfRandomVariable)
                / quantityOfIntervals);
        int indexOfCurrentPartOfImplementationsOfRandomVariable = 0;

        for (int indexOfInterval = 0; indexOfInterval < intervals.length; indexOfInterval++) {
            List<Double> currentPartOfImplementationsOfRandomVariableAsList = new ArrayList<>();
            List<Double> currentPartOfDistributionTableAsList = new ArrayList<>();

            while (implementationsOfRandomVariable[indexOfCurrentPartOfImplementationsOfRandomVariable]
                    < increment * (indexOfInterval + 1)) {
                currentPartOfImplementationsOfRandomVariableAsList
                        .add(implementationsOfRandomVariable[indexOfCurrentPartOfImplementationsOfRandomVariable]);
                currentPartOfDistributionTableAsList
                        .add(distributionTable[indexOfCurrentPartOfImplementationsOfRandomVariable]);
                indexOfCurrentPartOfImplementationsOfRandomVariable++;
            }

            double[] currentPartOfImplementationsOfRandomVariable
                    = convertListDoubleToArray(currentPartOfImplementationsOfRandomVariableAsList);
            double[] currentPartOfDistributionTable = convertListDoubleToArray(currentPartOfDistributionTableAsList);

            Distribution currentPartOfDistribution
                    = new Distribution(currentPartOfImplementationsOfRandomVariable, currentPartOfDistributionTable);
            intervals[indexOfInterval]
                    = new Interval(currentPartOfDistribution);
        }
        return intervals;
    }

    private double[] convertListDoubleToArray(List<Double> list) {
        int size = list.size();
        double[] doubles = new double[size];
        for (int index = 0; index < size; index++) {
            doubles[index] = list.get(index);
        }
        return doubles;
    }

    private double[] probabilitiesByBinomialLaw(int selectionSize, double probability) {
        double[] probabilities = new double[selectionSize];
        Random random = new Random();
        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            probabilities[indexOfProbability] = random.nextDouble();
        }
        return probabilities;
    }
}
