package ru.wkn;

import ru.wkn.utils.DistributionFactory;
import ru.wkn.distributors.Distributor;

public class DistributorFacade {

    public Distributor getDistributor(String typeOfDistributor) {
        DistributionFactory distributionFactoryIF;
        Distributor distributor;
        distributionFactoryIF = new DistributionFactory();
        distributor = distributionFactoryIF.createDistributionByFilenameOfProperty(typeOfDistributor);
        return distributor;
    }
}
