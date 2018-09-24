package ru.wkn;

import ru.wkn.distributionfactory.DistributionFactory;
import ru.wkn.distributions.IDistribution;

import java.io.IOException;
import java.util.Properties;

public class Core {

    public void writeDistributionInFile(String filename, String propertyFilename) {
        DistributionFactory distributionFactoryIF;
        try {
            Properties properties = new Properties();
            properties.load(Core.class.getResourceAsStream(propertyFilename));
            distributionFactoryIF = new DistributionFactory(properties);
            IDistribution iDistribution = distributionFactoryIF.createDistributionByFilename(filename);
            iDistribution.distribute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
