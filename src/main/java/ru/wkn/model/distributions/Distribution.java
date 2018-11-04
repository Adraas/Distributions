package ru.wkn.model.distributions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Distribution {

    private double[] randomSample;
    private double probability;

    public Distribution(double[] randomSample, double probability) {
        this.randomSample = randomSample;
        this.probability = probability;
    }

    public double[] getRandomSample() {
        return randomSample;
    }

    public double getProbability() {
        return probability;
    }

    public Interval[] intervals(int quantityOfIntervals) {
        Distribution distribution = this;
        Interval[] intervals = new Interval[quantityOfIntervals];
        Arrays.sort(distribution.getRandomSample());

        double[] randomSample = distribution.getRandomSample();
        double maxValueOfRandomSample
                = randomSample[randomSample.length - 1];
        double minValueOfRandomSample = randomSample[0];

        int increment = (int) ((maxValueOfRandomSample - minValueOfRandomSample)
                / quantityOfIntervals);
        if (increment == 0) {
            increment = 1;
        }

        int indexOfCurrentPartOfRandomSample = 0;
        int distributionPartSize = distribution.getRandomSample().length;

        for (int indexOfInterval = 0; indexOfInterval < quantityOfIntervals; indexOfInterval++) {
            List<Double> currentPartOfRandomVariableAsList = new ArrayList<>();

            while (indexOfCurrentPartOfRandomSample < distributionPartSize
                    && randomSample[indexOfCurrentPartOfRandomSample]
                    - (minValueOfRandomSample - 1) < increment * (indexOfInterval + 1)) {
                currentPartOfRandomVariableAsList
                        .add(randomSample[indexOfCurrentPartOfRandomSample]);
                indexOfCurrentPartOfRandomSample++;
            }

            double[] currentPartOfRandomSample
                    = convertListDoubleToArray(currentPartOfRandomVariableAsList);

            Distribution currentPartOfDistribution
                    = new Distribution(currentPartOfRandomSample, distribution.getProbability());
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

    public double[] theoreticalProbabilities() {
        int selectionSize = randomSample.length;
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
