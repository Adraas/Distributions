package ru.wkn.model.utils;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;

public class QualityControl {

    public static boolean isImplementationBelongsToDiscreteDistribution(
            Interval[] intervals,
            Distribution distribution,
            double thresholdValue) {
        int[] countsOfIntervals = countsOfIntervals(intervals);
        double criterionOfPearson = criterionOfPearson(distribution, countsOfIntervals);
        return !(criterionOfPearson > thresholdValue);
    }

    private static int[] countsOfIntervals(Interval[] intervals) {
        int quantityOfIntervals = intervals.length;
        int[] countsOfIntervals = new int[quantityOfIntervals];
        for (int indexOfInterval = 0; indexOfInterval < quantityOfIntervals; indexOfInterval++) {
            countsOfIntervals[indexOfInterval] = intervals[indexOfInterval].countOfIntervalValues();
        }
        return countsOfIntervals;
    }

    private static double criterionOfPearson(Distribution distribution, int[] countsOfIntervals) {
        double criterionOfPearson = 0;
        for (int countsOfInterval : countsOfIntervals) {
            double quantityOfProbabilities = distribution.getImplementationsOfRandomVariables().length
                    * distribution.getProbability();
            criterionOfPearson += Math.pow(countsOfInterval - quantityOfProbabilities, 2)
                    / quantityOfProbabilities;
        }
        return criterionOfPearson;
    }
}
