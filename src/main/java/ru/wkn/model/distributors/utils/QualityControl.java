package ru.wkn.model.distributors.utils;

import ru.wkn.model.distributions.Distribution;

public class QualityControl {

    public boolean isImplementationBelongsToDiscreteDistribution(Distribution distribution, int quantityOfIntervals,
                                                         double significanceLevel, double thresholdValue) {
        double[] probabilities = distribution.getProbabilities();
        double[] limitsForIntervals = limitsForIntervalsForDiscreteDistribution(distribution, quantityOfIntervals,
                significanceLevel);
        Interval[] intervals = new Interval[0];
        try {
            intervals = intervalsForDiscreteDistribution(limitsForIntervals, quantityOfIntervals);
        } catch (LimitException e) {
            e.printStackTrace();
        }
        int[] countsOfIntervals = countsOfIntervals(distribution, quantityOfIntervals, intervals);
        double criterionOfPearson = criterionOfPearson(distribution, quantityOfIntervals, probabilities,
                countsOfIntervals);
        return !(criterionOfPearson > thresholdValue);
    }

    private double[] limitsForIntervalsForDiscreteDistribution(Distribution distribution, int quantityOfIntervals,
                                                               double significanceLevel) { //TODO
        double[] probabilities = distribution.getProbabilities();
        double[] limitsForIntervals = new double[quantityOfIntervals];
        int size = probabilities.length;
        for (int indexOfPoint = 0; indexOfPoint < quantityOfIntervals; indexOfPoint++) {
            if (indexOfPoint < size) {
                limitsForIntervals[indexOfPoint] = probabilities[indexOfPoint];
            }
        }
        return limitsForIntervals;
    }

    private Interval[] intervalsForDiscreteDistribution(double[] limitsForIntervals, int quantityOfIntervals)
            throws LimitException { //TODO
        Interval[] intervals = new Interval[quantityOfIntervals + 1];
        intervals[0] = new Interval(Double.NEGATIVE_INFINITY, limitsForIntervals[0]);
        if (quantityOfIntervals > 2) {
            for (int indexOfPoint = 1; indexOfPoint < quantityOfIntervals - 2; indexOfPoint++) {
                intervals[indexOfPoint] = new Interval(limitsForIntervals[indexOfPoint],
                        limitsForIntervals[indexOfPoint + 1]);
            }
        }
        intervals[intervals.length - 1] = new Interval(limitsForIntervals[limitsForIntervals.length - 1],
                Double.POSITIVE_INFINITY);
        return intervals;
    }

    private int[] countsOfIntervals(Distribution distribution, int quantityOfIntervals, Interval[] intervals) {
        int size = quantityOfIntervals + 1;
        int[] countsOfIntervals = new int[size];
        int quantityOfImplementations = distribution.getImplementationOfRandomVariable().length;
        if (intervals.length > 1) {
            for (int indexOfInterval = 0; indexOfInterval < size; indexOfInterval++) {
                for (int indexOfImplementation = 0;
                     indexOfImplementation < quantityOfImplementations;
                     indexOfImplementation++) {
                    if (intervals[indexOfInterval]
                            .isItContainsAnElement(distribution
                                    .getImplementationOfRandomVariable()[indexOfImplementation])) {
                        countsOfIntervals[indexOfInterval]++;
                    }
                }
            }
        }
        return countsOfIntervals;
    }

    private double criterionOfPearson(Distribution distribution, int quantityOfIntervals, double[] probabilities,
                                      int[] countsOfIntervals) {
        double criterionOfPearson = 0;
        int selectionSize = distribution.getImplementationOfRandomVariable().length;
        for (int indexOfIteration = 0; indexOfIteration < quantityOfIntervals; indexOfIteration++) {
            double quantityOfProbabilities = selectionSize * probabilities[indexOfIteration];
            criterionOfPearson += Math.pow(countsOfIntervals[indexOfIteration]
                    - quantityOfProbabilities, 2) / quantityOfProbabilities;
        }
        return criterionOfPearson;
    }

    private class Interval {

        private double leftLimit;
        private double rightLimit;

        Interval(double leftLimit, double rightLimit) throws LimitException {
            if (leftLimit >= rightLimit) {
                throw new LimitException("Wrong interval's limits");
            }
            this.leftLimit = leftLimit;
            this.rightLimit = rightLimit;
        }

        boolean isItContainsAnElement(double implementationOfRandomVariable) {
            return implementationOfRandomVariable > leftLimit && implementationOfRandomVariable < rightLimit;
        }
    }
}
