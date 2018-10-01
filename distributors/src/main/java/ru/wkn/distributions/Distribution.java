package ru.wkn.distributions;

public class Distribution {

    private double[] distributionOfRandomVariables;

    public Distribution(double[] distributionOfRandomVariables) {
        this.distributionOfRandomVariables = distributionOfRandomVariables;
    }

    public double[] getDistributionOfRandomVariables() {
        return distributionOfRandomVariables;
    }
}
