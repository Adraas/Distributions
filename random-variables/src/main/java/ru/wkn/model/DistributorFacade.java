package ru.wkn.model;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributions.Interval;
import ru.wkn.distributors.DistributionFactory;
import ru.wkn.distributors.Distributor;
import ru.wkn.distributors.discrete.BinomialDistributor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DistributorFacade {

    private Distributor distributor;
    private Distribution distribution;
    private Interval[] intervals;

    public Distributor getDistributor(String typeOfDistributor) {
        if (distributor == null) {
            DistributionFactory distributionFactoryIF;
            distributionFactoryIF = new DistributionFactory();
            distributor = distributionFactoryIF.createDistributorByType(typeOfDistributor);
        }
        return distributor;
    }

    public Distribution getBinomialDistribution(int selectionSize, int valueRange, double probability) {
        if (distribution == null) {
            try {
                InputStream inputStream = DistributorFacade.class
                        .getResourceAsStream("/distribution-parameters/binomial.properties");
                Properties properties = new Properties();

                properties.load(inputStream);
                properties.setProperty("selectionSize", String.valueOf(selectionSize));
                properties.setProperty("valueRange", String.valueOf(valueRange));
                properties.setProperty("probability", String.valueOf(probability));

                distribution = ((BinomialDistributor) distributor).getDistribution(properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return distribution;
    }

    public Interval[] getIntervals(int quantityOfIntervals) {
        return intervals != null ? intervals : (intervals = distribution.intervals(quantityOfIntervals));
    }
}
