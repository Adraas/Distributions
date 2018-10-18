package ru.wkn.model.distributors.discrete;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;
import ru.wkn.model.distributors.Distributor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinomialDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, int valueRange, double probability) {
        double[] distributionTable = probabilitiesByBinomialLaw(selectionSize, probability);
        double[] implementationsOfRandomVariables = new double[selectionSize];
        int currentImplementationOfRandomVariable;
        double randomValue;

        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            currentImplementationOfRandomVariable = 0;

            for (int i = 0; i < valueRange; i++) {
                randomValue = super.getRandomValue();
                if (randomValue < distributionTable[indexOfProbability]) {
                    currentImplementationOfRandomVariable++;
                }
            }
            implementationsOfRandomVariables[indexOfProbability] = currentImplementationOfRandomVariable;
        }
        return new Distribution(implementationsOfRandomVariables, distributionTable);
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
        double probabilityOfFailure = 1 - probability;
        for (int indexOfProbability = 0; indexOfProbability < selectionSize; indexOfProbability++) {
            probabilities[indexOfProbability] = combinations(selectionSize, indexOfProbability)
                    * Math.pow(probability, indexOfProbability)
                    * Math.pow(probabilityOfFailure, selectionSize - indexOfProbability);
        }
        return probabilities;
    }

    private int combinations(int generalValue, int currentValue) {
        int combinations = 0;
        if (currentValue <= generalValue) {
            combinations
                    = generalValue == 0 && currentValue == 0 ? 1
                    : currentValue == 0 ? 1
                    : generalValue == currentValue ? 1
                    : factorialByFunctionOfStirling(generalValue)
                    / (factorialByFunctionOfStirling(currentValue)
                    * factorialByFunctionOfStirling(generalValue - currentValue));
        }
        return combinations;
    }

    private int factorialByFunctionOfStirling(int value) {
        return (int) Math.round(Math.sqrt(2 * Math.PI * value) * Math.pow(value / Math.E, value));
    }
}
