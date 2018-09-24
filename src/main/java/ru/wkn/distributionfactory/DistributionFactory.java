package ru.wkn.distributionfactory;

import ru.wkn.distributions.BinaryDistribution;
import ru.wkn.distributions.IDistribution;
import ru.wkn.distributions.PoissonDistribution;
import ru.wkn.distributions.UniformDistribution;

import java.util.Properties;

public class DistributionFactory implements DistributionFactoryIF<IDistribution> {

    private Properties properties;

    public DistributionFactory(Properties properties) {
        this.properties = properties;
    }

    @Override
    public IDistribution createDistributionByFilename(String filename) {
        return filename.equals("poisson.txt") ? new PoissonDistribution(properties)
                : filename.equals("uniform.txt") ? new UniformDistribution(properties)
                : filename.equals("binary.txt") ? new BinaryDistribution(properties)
                : null;
    }
}
