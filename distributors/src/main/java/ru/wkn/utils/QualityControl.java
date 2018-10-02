package ru.wkn.utils;

import ru.wkn.distributions.Distribution;

public class QualityControl {

    private double significanceLevel = 0.05;

    public double getSignificanceLevel() {
        return significanceLevel;
    }

    public void setSignificanceLevel(double significanceLevel) {
        this.significanceLevel = significanceLevel;
    }

    public boolean isRealizationBelongsToDistribution(Distribution distribution, int inputParameter, double coefficientForSegmentation, double thresholdValue) {
        double[] probabilities = distribution.getProbabilities();
        Interval[] intervals = intervals(inputParameter, coefficientForSegmentation);
        int[] countsOfIntervals = countsOfIntervals(distribution, inputParameter, intervals);
        double criterionOfPearson = criterionOfPearson(distribution, inputParameter, probabilities, countsOfIntervals);
        return !(criterionOfPearson > thresholdValue);
    }

    private Interval[] intervals(int inputParameter, double coefficientForSegmentation) {
        double[] points = new double[inputParameter];
        for (int indexOfPoint = 0; indexOfPoint < inputParameter; indexOfPoint++) {
            points[indexOfPoint] = (1 / coefficientForSegmentation)
                    * (-Math.log(1 - indexOfPoint / coefficientForSegmentation));
        }
        Interval[] intervals = new Interval[inputParameter + 1];
        intervals[0] = new Interval(Double.NEGATIVE_INFINITY, points[0]);
        if (inputParameter > 2) {
            for (int indexOfPoint = 1; indexOfPoint < inputParameter - 2; indexOfPoint++) {
                intervals[indexOfPoint] = new Interval(points[indexOfPoint], points[indexOfPoint + 1]);
            }
        }
        intervals[intervals.length - 1] = new Interval(points[points.length - 1], Double.POSITIVE_INFINITY);
        return intervals;
    }

    private int[] countsOfIntervals(Distribution distribution, int inputParameter, Interval[] intervals) {
        int quantityOfIntervals = inputParameter + 1;
        int[] countsOfIntervals = new int[quantityOfIntervals];
        int quantityOfImplementations = distribution.getDistributionOfRandomVariables().length;
        if (intervals.length > 1) {
            for (int indexOfInterval = 0; indexOfInterval < quantityOfIntervals; indexOfInterval++) {
                for (int indexOfImplementation = 0;
                     indexOfImplementation < quantityOfImplementations;
                     indexOfImplementation++) {
                    if (intervals[indexOfInterval]
                            .isItContainsAnElement(distribution
                                    .getDistributionOfRandomVariables()[indexOfImplementation])) {
                        countsOfIntervals[indexOfInterval]++;
                    }
                }
            }
        }
        return countsOfIntervals;
    }

    private double criterionOfPearson(Distribution distribution, int inputParameter, double[] probabilities, int[] countsOfIntervals) {
        double criterionOfPearson = 0;
        int selectionSize = distribution.getDistributionOfRandomVariables().length;
        for (int indexOfIteration = 0; indexOfIteration < inputParameter; indexOfIteration++) {
            double quantityOfProbabilities = selectionSize * probabilities[indexOfIteration];
            criterionOfPearson += Math.pow(countsOfIntervals[indexOfIteration]
                    - quantityOfProbabilities, 2) / quantityOfProbabilities;
        }
        return criterionOfPearson;
    }

    private class Interval {

        private double leftLimit;
        private double rightLimit;

        Interval(double leftLimit, double rightLimit) {
            this.leftLimit = leftLimit;
            this.rightLimit = rightLimit;
        }

        boolean isItContainsAnElement(double implementationOfRandomVariable) {
            return implementationOfRandomVariable > leftLimit && implementationOfRandomVariable < rightLimit;
        }
    }
}
