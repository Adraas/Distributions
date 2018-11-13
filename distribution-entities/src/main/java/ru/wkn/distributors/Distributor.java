package ru.wkn.distributors;

import java.util.Random;

public abstract class Distributor {

    protected double getRandomValue() {
        Random random = new Random();
        return random.nextDouble();
    }
}
