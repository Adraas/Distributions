package ru.wkn.distributions;

public class Distribution {

    private double[] distributionOfRandomVariables;
    private double[] probabilities;
    private int inputParameter;

    public Distribution(double[] distributionOfRandomVariables, double[] probabilities, int inputParameter) {
        this.distributionOfRandomVariables = distributionOfRandomVariables;
        this.probabilities = probabilities;
        this.inputParameter = inputParameter;
    }

    public double[] getDistributionOfRandomVariables() {
        return distributionOfRandomVariables;
    }

    public double[] getProbabilities() {
        return probabilities;
    }

    public int getInputParameter() {
        return inputParameter;
    }
}
