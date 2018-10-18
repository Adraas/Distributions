package ru.wkn.model.utils;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;

public class QualityControl {

    public static boolean isImplementationBelongsToDiscreteDistribution(
            Interval[] intervals,
            Distribution distribution,
            double thresholdValue) {
        double[] distributionTable = distribution.getDistributionTable();
        int[] countsOfIntervals = countsOfIntervals(intervals);
        double criterionOfPearson = criterionOfPearson(distributionTable, countsOfIntervals);
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

    private static double criterionOfPearson(double[] distributionTable, int[] countsOfIntervals) {
        int quantityOfIntervals = countsOfIntervals.length;
        double criterionOfPearson = 0;
        int selectionSize = distributionTable.length;
        for (int indexOfIteration = 0; indexOfIteration < quantityOfIntervals; indexOfIteration++) {
            double quantityOfProbabilities = selectionSize * distributionTable[indexOfIteration];
            criterionOfPearson += Math.pow(countsOfIntervals[indexOfIteration] - quantityOfProbabilities, 2)
                    / quantityOfProbabilities;
        }
        return criterionOfPearson;
    }
}
