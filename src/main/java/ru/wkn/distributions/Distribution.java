package ru.wkn.distributions;

import java.util.Random;

public abstract class Distribution implements IDistribution {

    protected double getRandomValue(double alpha) {
        Random random = new Random();
        double randomValue;
        do {
            randomValue = random.nextDouble();
        } while (randomValue == alpha);
        return randomValue;
    }
}
