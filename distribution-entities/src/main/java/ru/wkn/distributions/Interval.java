package ru.wkn.distributions;

public class Interval {

    private Distribution partOfDistribution;

    public Interval(Distribution partOfDistribution) {
        this.partOfDistribution = partOfDistribution;
    }

    public int countOfIntervalValues() {
        return partOfDistribution.getRandomSample().length;
    }

    public Distribution getPartOfDistribution() {
        return partOfDistribution;
    }
}
