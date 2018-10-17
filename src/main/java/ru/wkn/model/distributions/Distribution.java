package ru.wkn.model.distributions;

public class Distribution {

    private double[] implementationsOfRandomVariables;
    private double[] probabilities;

    public Distribution(double[] implementationsOfRandomVariables, double[] probabilities) {
        this.implementationsOfRandomVariables = implementationsOfRandomVariables;
        this.probabilities = probabilities;
    }

    public double[] getImplementationsOfRandomVariable() {
        return implementationsOfRandomVariables;
    }

    public double[] getProbabilities() {
        return probabilities;
    }
}
