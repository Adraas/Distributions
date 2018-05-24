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
    public IDistribution createDistribution(String prefixName) {
        return prefixName.equals("poisson") ? new PoissonDistribution(properties, writer)
                : prefixName.equals("uniform") ? new UniformDistribution(properties, writer)
                : null;
    }
}
