package ru.wkn.model.distributors.discrete;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;
import ru.wkn.model.distributors.Distributor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Interval[] intervalsOfDistribution(Distribution distribution, int quantityOfIntervals) {
        Interval[] intervals = new Interval[quantityOfIntervals];
        Arrays.sort(distribution.getImplementationsOfRandomVariables());

        double[] implementationsOfRandomVariable = distribution.getImplementationsOfRandomVariables();
        double maxValueOfImplementationOfRandomVariable
                = implementationsOfRandomVariable[implementationsOfRandomVariable.length - 1];
        double minValueOfImplementationOfRandomVariable = implementationsOfRandomVariable[0];
        int increment = (int) ((maxValueOfImplementationOfRandomVariable - minValueOfImplementationOfRandomVariable)
                / quantityOfIntervals);
        int indexOfCurrentPartOfImplementationsOfRandomVariable = 0;

        for (int indexOfInterval = 0; indexOfInterval < intervals.length; indexOfInterval++) {
            List<Double> currentPartOfImplementationsOfRandomVariableAsList = new ArrayList<>();

            while (implementationsOfRandomVariable[indexOfCurrentPartOfImplementationsOfRandomVariable]
                    < increment * (indexOfInterval + 1)) {
                currentPartOfImplementationsOfRandomVariableAsList
                        .add(implementationsOfRandomVariable[indexOfCurrentPartOfImplementationsOfRandomVariable]);
            }

            double[] currentPartOfImplementationsOfRandomVariable
                    = convertListDoubleToArray(currentPartOfImplementationsOfRandomVariableAsList);

            Distribution currentPartOfDistribution
                    = new Distribution(currentPartOfImplementationsOfRandomVariable, distribution.getProbability());
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
}
