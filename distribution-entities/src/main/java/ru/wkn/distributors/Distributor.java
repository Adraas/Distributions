package ru.wkn.distributors;

import ru.wkn.distributions.Distribution;

import java.util.Properties;
import java.util.Random;

public abstract class Distributor {

    protected double getRandomProbability() {
        Random random = new Random();
        return random.nextDouble();
    }

    public abstract Distribution getDistribution(Properties properties);

    public abstract double[] theoreticalProbabilities();
}
