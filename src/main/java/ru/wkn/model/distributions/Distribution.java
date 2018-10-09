package ru.wkn.model.distributions;

public class Distribution {

    private double[] distributionOfRandomVariables;
    private double[] probabilities;

    public Distribution(double[] distributionOfRandomVariables, double[] probabilities) {
        this.distributionOfRandomVariables = distributionOfRandomVariables;
        this.probabilities = probabilities;
    }

    public double[] getDistributionOfRandomVariables() {
        return distributionOfRandomVariables;
    }

    public double[] getProbabilities() {
        return probabilities;
    }
}
