package ru.wkn.model;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributions.Interval;
import ru.wkn.distributors.DistributionFactory;
import ru.wkn.distributors.Distributor;
import ru.wkn.distributors.discrete.BinomialDistributor;

public class DistributorFacade {

    private Distributor distributor;
    private Distribution distribution;
    private Interval[] intervals;

    public void setDistributor(String typeOfDistributor) {
        if (distributor == null) {
            DistributionFactory distributionFactoryIF;
            distributionFactoryIF = new DistributionFactory();
            distributor = distributionFactoryIF.createDistributorByType(typeOfDistributor);
        }
    }

    public Distribution getBinomialDistribution(int selectionSize, int valueRange, double probability) {
        if (distribution == null) {
            distribution = ((BinomialDistributor) distributor).getDistribution(selectionSize, valueRange, probability);
        }
        return distribution;
    }

    public Interval[] getIntervals(int quantityOfIntervals) {
        return intervals != null ? intervals : (intervals = distribution.intervals(quantityOfIntervals));
    }
}
