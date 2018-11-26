package ru.wkn.distributors;

import ru.wkn.distributors.discrete.BinomialDistributor;
import ru.wkn.distributors.discrete.PoissonDistributor;

public class DistributionFactory implements DistributionFactoryIF<Distributor> {

    @Override
    public Distributor createDistributorByType(String typeOfDistributor) {
        return typeOfDistributor.equals("poisson-distributor") ? new PoissonDistributor()
                : typeOfDistributor.equals("binomial-distributor") ? new BinomialDistributor()
                : null;
    }
}
