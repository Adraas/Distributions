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

    public boolean isRealizationBelongsToDistribution(Distribution distribution, double coefficientForSegmentation, double thresholdValue) {
        Interval[] intervals = intervals(distribution, coefficientForSegmentation);
        int[] countsOfIntervals = countsOfIntervals(distribution, intervals);
        double criterionOfPearson = criterionOfPearson(distribution, countsOfIntervals);
        return !(criterionOfPearson > thresholdValue);
    }

    private Interval[] intervals(Distribution distribution, double coefficientForSegmentation) {
        int quantityOfPoints = distribution.getInputParameter();
        double[] points = new double[quantityOfPoints];
        for (int indexOfPoint = 0; indexOfPoint < quantityOfPoints; indexOfPoint++) {
            points[indexOfPoint] = (1 / coefficientForSegmentation)
                    * (-Math.log(1 - indexOfPoint / coefficientForSegmentation));
        }
        Interval[] intervals = new Interval[quantityOfPoints + 1];
        intervals[0] = new Interval(Double.NEGATIVE_INFINITY, points[0]);
        if (quantityOfPoints > 2) {
            for (int indexOfPoint = 1; indexOfPoint < quantityOfPoints - 2; indexOfPoint++) {
                intervals[indexOfPoint] = new Interval(points[indexOfPoint], points[indexOfPoint + 1]);
            }
        }
        intervals[intervals.length - 1] = new Interval(points[points.length - 1], Double.POSITIVE_INFINITY);
        return intervals;
    }

    private int[] countsOfIntervals(Distribution distribution, Interval[] intervals) {
        int quantityOfIntervals = distribution.getInputParameter() + 1;
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

    private double criterionOfPearson(Distribution distribution, int[] countsOfIntervals) {
        double criterionOfPearson = 0;
        int selectionSize = distribution.getDistributionOfRandomVariables().length;
        int quantityOfIterations = distribution.getInputParameter();
        for (int indexOfIteration = 0; indexOfIteration < quantityOfIterations; indexOfIteration++) {
            double quantityOfProbabilities = selectionSize * distribution.getProbabilities()[indexOfIteration];
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
