package ru.wkn.distributionfactory;

import ru.wkn.distributionfactory.distributions.IDistribution;
import ru.wkn.distributionfactory.distributions.PoissonDistribution;
import ru.wkn.distributionfactory.distributions.UniformDistribution;

import java.io.Writer;
import java.util.Properties;

public class DistributionFactory implements DistributionFactoryIF<IDistribution> {

    private Properties properties;
    private Writer writer;

    public DistributionFactory(Properties properties, Writer writer) {
        this.properties = properties;
        this.writer = writer;
    }

    @Override
    public IDistribution createDistributionByFilename(String filename) {
        return filename.equals("poisson.txt") ? new PoissonDistribution(properties, writer)
                : filename.equals("uniform.txt") ? new UniformDistribution(properties, writer)
                : null;
    }
}
