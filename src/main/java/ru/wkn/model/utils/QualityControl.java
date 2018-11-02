package ru.wkn.model.utils;

import ru.wkn.model.distributions.Interval;

public class QualityControl {

    public static boolean isImplementationBelongsToDiscreteDistribution(
            Interval[] intervals,
            double thresholdValue) {
        int[] experimentalFrequencies = getExperimentalFrequencies(intervals);
        int[] theoreticalFrequencies = getTheoreticalFrequencies(intervals);
        double criterionOfPearson = getCriterionOfPearson(experimentalFrequencies, theoreticalFrequencies);
        return !(criterionOfPearson > thresholdValue);
    }

    private static int[] getExperimentalFrequencies(Interval[] intervals) {
        int quantityOfIntervals = intervals.length;
        int[] frequencies = new int[quantityOfIntervals];
        for (int indexOfInterval = 0; indexOfInterval < quantityOfIntervals; indexOfInterval++) {
            frequencies[indexOfInterval] = intervals[indexOfInterval].countOfIntervalValues();
        }
        return frequencies;
    }

    private static int[] getTheoreticalFrequencies(Interval[] intervals) {
        int quantityOfIntervals = intervals.length;
        int[] frequencies = new int[quantityOfIntervals];
        double probability;
        for (int indexOfInterval = 0; indexOfInterval < quantityOfIntervals; indexOfInterval++) {
            probability = intervals[indexOfInterval].getPartOfDistribution().getProbability();
            for (double randomVariable
                    : intervals[indexOfInterval].getPartOfDistribution().getImplementationsOfRandomVariables()) {
                frequencies[indexOfInterval] += probability * randomVariable;
            }
        }
        return frequencies;
    }

    private static double getCriterionOfPearson(int[] experimentalFrequencies, int[] theoreticalFrequencies) {
        double criterionOfPearson = 0;
        int iterations = experimentalFrequencies.length;
        for (int i = 0; i < iterations; i++) {
            if (theoreticalFrequencies[i] != 0) {
                criterionOfPearson += Math.pow(experimentalFrequencies[i] - theoreticalFrequencies[i], 2)
                        / theoreticalFrequencies[i];
            }
        }
        return criterionOfPearson;
    }
}
