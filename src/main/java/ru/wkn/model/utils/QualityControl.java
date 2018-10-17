package ru.wkn.model.utils;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;
import ru.wkn.model.distributions.LimitException;

import java.util.ArrayList;
import java.util.List;

public class QualityControl {

    public boolean isImplementationBelongsToDiscreteDistribution(
            Distribution distribution,
            double significanceLevel,
            double thresholdValue) {
        double[] probabilities = distribution.getProbabilities();
        int[] limitsForIntervals = limitsForIntervalsForDiscreteDistribution(distribution, significanceLevel);
        Interval[] intervals = new Interval[0];
        try {
            intervals = intervalsForDiscreteDistribution(distribution, limitsForIntervals);
        } catch (LimitException e) {
            e.printStackTrace();
        }
        int[] countsOfIntervals = countsOfIntervals(distribution, intervals);
        double criterionOfPearson = criterionOfPearson(probabilities, countsOfIntervals);
        return !(criterionOfPearson > thresholdValue);
    }

    private int[] limitsForIntervalsForDiscreteDistribution(Distribution distribution, double significanceLevel) {
        double[] probabilities = distribution.getProbabilities();
        List<Integer> limitsOfIntervals = new ArrayList<>();
        int sizeOfSelection = probabilities.length;
        int indexOfProbability = 0;
        int currentLimit = 0;
        while (indexOfProbability < sizeOfSelection) {
            if (probabilities[indexOfProbability] < significanceLevel) {
                currentLimit += probabilities[indexOfProbability];
                indexOfProbability++;
            } else {
                limitsOfIntervals.add(currentLimit);
            }
        }
        return parseListToDoubleArray(limitsOfIntervals);
    }

    private int[] parseListToDoubleArray(List<Integer> list) {
        int size = list.size();
        int[] intValues = new int[size];
        for (int index = 0; index < size; index++) {
            intValues[index] = list.get(index);
        }
        return intValues;
    }

    private Interval[] intervalsForDiscreteDistribution(Distribution distribution, int[] limitsOfIntervals) throws LimitException {
        int quantityOfIntervals = limitsOfIntervals.length - 1;
        Interval[] intervals = new Interval[quantityOfIntervals];
        int indexOfImplementationOfRandomVariable = 0;
        for (int indexOfLimit = 1; indexOfLimit < quantityOfIntervals; indexOfLimit++) {
            intervals[indexOfLimit] = new Interval(limitsOfIntervals[indexOfLimit], limitsOfIntervals[indexOfLimit + 1]);
            while (indexOfImplementationOfRandomVariable <= limitsOfIntervals[indexOfLimit]) {
                intervals[indexOfLimit].addNewImplementationOfRandomVariable(distribution.getImplementationsOfRandomVariable()[indexOfImplementationOfRandomVariable]);
                indexOfImplementationOfRandomVariable++;
            }
        }
        return intervals;
    }

    private int[] countsOfIntervals(Distribution distribution, Interval[] intervals) {
        int quantityOfIntervals = intervals.length;
        int[] countsOfIntervals = new int[quantityOfIntervals];
        int quantityOfImplementations = distribution.getImplementationsOfRandomVariable().length;
        for (int indexOfInterval = 0; indexOfInterval < quantityOfImplementations; indexOfInterval++) {
            countsOfIntervals[indexOfInterval] = intervals[indexOfInterval].countOfIntervalValues();
        }
        return countsOfIntervals;
    }

    private double criterionOfPearson(double[] probabilities, int[] countsOfIntervals) {
        int quantityOfIntervals = countsOfIntervals.length;
        double criterionOfPearson = 0;
        int sizeOfSelection = probabilities.length;
        for (int indexOfIteration = 0; indexOfIteration < quantityOfIntervals; indexOfIteration++) {
            double quantityOfProbabilities = sizeOfSelection * probabilities[indexOfIteration];
            criterionOfPearson += Math.pow(countsOfIntervals[indexOfIteration] - quantityOfProbabilities, 2) / quantityOfProbabilities;
        }
        return criterionOfPearson;
    }
}
