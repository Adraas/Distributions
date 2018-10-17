package ru.wkn.model.distributions;

public class Interval {

    private int leftLimit;
    private int rightLimit;
    private Distribution partOfDistribution;

    public Interval(int leftLimit, int rightLimit, Distribution partOfDistribution) throws LimitException {
        if (leftLimit >= rightLimit) {
            throw new LimitException("Wrong interval's limits");
        }
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
        this.partOfDistribution = partOfDistribution;
    }

    public int getLeftLimit() {
        return leftLimit;
    }

    public int getRightLimit() {
        return rightLimit;
    }

    public int countOfIntervalValues() {
        return partOfDistribution.getImplementationsOfRandomVariable().length;
    }

    public Distribution getPartOfDistribution() {
        return partOfDistribution;
    }
}
