package ru.wkn.streams;

import ru.wkn.distributions.Distribution;

public class PalmStream implements Stream {

    @Override
    public double[] initTimeIntervals(Distribution distribution, int timeCoefficient) {
        double[] randomSample = distribution.getRandomSample();
        int size = randomSample.length;
        double[] timeIntervals = new double[size];

        for (int i = 0; i < size; i++) {
            timeIntervals[i] = timeCoefficient + randomSample[i];
        }
        return timeIntervals;
    }
}
