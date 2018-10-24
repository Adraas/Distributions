package ru.wkn.model.distributions;

public class Distribution {

    private double[] implementationsOfRandomVariables;
    private double probability;

    public Distribution(double[] implementationsOfRandomVariables, double probability) {
        this.implementationsOfRandomVariables = implementationsOfRandomVariables;
        this.probability = probability;
    }

    public double[] getImplementationsOfRandomVariables() {
        return implementationsOfRandomVariables;
    }

    public double getProbability() {
        return probability;
    }
}
