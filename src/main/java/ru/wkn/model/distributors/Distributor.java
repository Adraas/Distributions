package ru.wkn.model.distributors;

import java.util.Random;

public abstract class Distributor {

    protected double getRandomValue(double alpha) {
        Random random = new Random();
        double randomValue;
        do {
            randomValue = random.nextDouble();
        } while (randomValue == alpha);
        return randomValue;
    }
}
