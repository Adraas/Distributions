package ru.wkn.model.distributors;

import ru.wkn.model.distributors.continuous.UniformDistributor;
import ru.wkn.model.distributors.discrete.BinomialDistributor;
import ru.wkn.model.distributors.discrete.PoissonDistributor;

public class DistributionFactory implements DistributionFactoryIF<Distributor> {

    @Override
    public Distributor createDistributorByType(String typeOfDistributor) {
        return typeOfDistributor.equals("poisson-distributor") ? new PoissonDistributor()
                : typeOfDistributor.equals("uniform-distributor") ? new UniformDistributor()
                : typeOfDistributor.equals("binomial-distributor") ? new BinomialDistributor()
                : null;
    }
}
