package ru.wkn.model;

import ru.wkn.distributions.Distribution;
import ru.wkn.distributors.Distributor;
import ru.wkn.distributors.DistributorFactoryIF;
import ru.wkn.streams.Stream;
import ru.wkn.streams.StreamFactoryIF;

import java.util.Properties;

public class InformationStreamsFacade {

    private Distributor distributor;
    private Distribution distribution;
    private Stream stream;

    public void initDistributor(String typeOfDistributor) {
        DistributorFactoryIF<Distributor> distributorFactoryIF = DistributorFactoryIF.getDistributorFactoryByDefault();
        distributor = distributorFactoryIF.createDistributorByType(typeOfDistributor);
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void initDistribution(Properties properties) {
        distribution = distributor.getDistribution(properties);
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void initStream(String typeOfStream) {
        StreamFactoryIF<Stream> streamStreamFactoryIF = StreamFactoryIF.getStreamFactoryByDefault();
        stream = streamStreamFactoryIF.createStreamByType(typeOfStream);
    }

    public Stream getStream() {
        return stream;
    }
}
