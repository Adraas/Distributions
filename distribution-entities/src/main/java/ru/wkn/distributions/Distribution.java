package ru.wkn.distributions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Distribution {

    private double[] randomSample;
    private double probability;

    public Distribution(double[] randomSample) {
        this.randomSample = randomSample;
        Arrays.sort(randomSample);
    }

    public Distribution(double[] randomSample, double probability) {
        this.randomSample = randomSample;
        this.probability = probability;

        Arrays.sort(this.randomSample);
    }

    public double[] getRandomSample() {
        return randomSample;
    }

    public double getProbability() {
        return probability;
    }

    public Interval[] intervals(int quantityOfIntervals) {
        Interval[] intervals = new Interval[quantityOfIntervals];

        double maxValueOfRandomSample
                = randomSample[randomSample.length - 1];
        double minValueOfRandomSample = randomSample[0];

        int increment = (int) ((maxValueOfRandomSample - minValueOfRandomSample)
                / quantityOfIntervals);
        if (increment == 0) {
            increment = 1;
        }

        int indexOfCurrentPartOfRandomSample = 0;
        int distributionPartSize = randomSample.length;

        for (int indexOfInterval = 0; indexOfInterval < quantityOfIntervals; indexOfInterval++) {
            List<Double> currentPartOfRandomSampleAsList = new ArrayList<>();

            while (indexOfCurrentPartOfRandomSample < distributionPartSize
                    && randomSample[indexOfCurrentPartOfRandomSample]
                    - (minValueOfRandomSample - 1) < increment * (indexOfInterval + 1)) {
                currentPartOfRandomSampleAsList
                        .add(randomSample[indexOfCurrentPartOfRandomSample]);
                indexOfCurrentPartOfRandomSample++;
            }

            double[] currentPartOfRandomSample
                    = convertListDoubleToArray(currentPartOfRandomSampleAsList);

            Distribution currentPartOfDistribution
                    = new Distribution(currentPartOfRandomSample, probability);
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