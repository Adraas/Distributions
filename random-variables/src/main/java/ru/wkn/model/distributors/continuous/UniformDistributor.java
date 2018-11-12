package ru.wkn.model.distributors.continuous;

import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributors.Distributor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UniformDistributor extends Distributor {

    public Distribution getDistribution(int selectionSize, double a, double b, double probability) {
        double currentValueOfDistribution;
        double[] distribution = new double[selectionSize];
        for (int index = 0; index < selectionSize; index++) {
            currentValueOfDistribution = super.getRandomValue();
            currentValueOfDistribution = currentValueOfDistribution * a + b;
            currentValueOfDistribution = new BigDecimal(currentValueOfDistribution).setScale(5, RoundingMode.UP).doubleValue();
            distribution[index] = currentValueOfDistribution;
        }
        return new Distribution(distribution, probability);
    }
}
