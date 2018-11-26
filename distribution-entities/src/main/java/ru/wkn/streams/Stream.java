package ru.wkn.streams;

import ru.wkn.distributions.Distribution;

public interface Stream {

    double[] initTimeIntervals(Distribution distribution, int timeCoefficient);
}
