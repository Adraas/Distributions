package ru.wkn.model.distributors;

import ru.wkn.model.distributors.utils.DistributionFactory;

public class DistributorFacade {

    public Distributor getDistributor(String typeOfDistributor) {
        DistributionFactory distributionFactoryIF;
        Distributor distributor;
        distributionFactoryIF = new DistributionFactory();
        distributor = distributionFactoryIF.createDistributorByType(typeOfDistributor);
        return distributor;
    }
}
