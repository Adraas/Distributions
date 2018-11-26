package ru.wkn.model;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributions.Interval;
import ru.wkn.distributors.DistributionFactory;
import ru.wkn.distributors.Distributor;

import java.util.Properties;

public class DistributorFacade {

    private Distributor distributor;
    private Distribution distribution;
    private Interval[] intervals;

    public void initDistributor(String typeOfDistributor) {
        DistributionFactory distributionFactoryIF = new DistributionFactory();
        distributor = distributionFactoryIF.createDistributorByType(typeOfDistributor);
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void initDistribution(Properties properties) {
        distribution = distributor.getDistribution(properties);
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void initIntervals(int quantityOfIntervals) {
        intervals = distribution.intervals(quantityOfIntervals);
    }

    public Interval[] getIntervals() {
        return intervals;
    }
}
