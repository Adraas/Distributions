package ru.wkn.model;

import ru.wkn.model.distributors.DistributionFactory;
import ru.wkn.model.distributors.Distributor;

public class DistributorFacade {

    public Distributor getDistributor(String typeOfDistributor) {
        DistributionFactory distributionFactoryIF;
        Distributor distributor;
        distributionFactoryIF = new DistributionFactory();
        distributor = distributionFactoryIF.createDistributorByType(typeOfDistributor);
        return distributor;
    }
}
