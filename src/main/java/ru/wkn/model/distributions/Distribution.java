package ru.wkn.model.distributions;

public class Distribution {

    private double[] implementationOfRandomVariables;
    private double[] probabilities;

    public Distribution(double[] implementationOfRandomVariables, double[] probabilities) {
        this.implementationOfRandomVariables = implementationOfRandomVariables;
        this.probabilities = probabilities;
    }

    public double[] getImplementationOfRandomVariable() {
        return implementationOfRandomVariables;
    }

    public double[] getProbabilities() {
        return probabilities;
    }
}
