package ru.wkn.model.distributions;

public class Interval {

    private Distribution partOfDistribution;

    public Interval(Distribution partOfDistribution) {
        this.partOfDistribution = partOfDistribution;
    }

    public int countOfIntervalValues() {
        return partOfDistribution.getImplementationsOfRandomVariable().length;
    }

    public Distribution getPartOfDistribution() {
        return partOfDistribution;
    }
}