package ru.wkn;

import ru.wkn.distributionfactory.DistributionFactory;
import ru.wkn.distributionfactory.distributions.IDistribution;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class Core {

    public void writeDistributionInFile(String filename, String propertyFilename) {
        DistributionFactory distributionFactoryIF;
        try (PrintWriter printWriter = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filename),
                        Charset.forName("UTF-8").newEncoder()))) {
            Properties properties = new Properties();
            properties.load(Core.class.getResourceAsStream(propertyFilename));
            distributionFactoryIF = new DistributionFactory(properties, printWriter);
            IDistribution iDistribution = distributionFactoryIF.createDistributionByFilename(filename);
            iDistribution.distribute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
