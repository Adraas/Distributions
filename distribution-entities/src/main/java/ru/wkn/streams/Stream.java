package ru.wkn.streams;

import ru.wkn.distributions.Distribution;

public abstract class Stream {

    public abstract double[] initTimeIntervals(Distribution distribution, int timeCoefficient);
}
