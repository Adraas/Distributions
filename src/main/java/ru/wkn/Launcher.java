package ru.wkn;

import ru.wkn.distributionfactory.DistributionFactory;
import ru.wkn.distributionfactory.distributions.IDistribution;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class Launcher {

    public static void main(String[] args) {
        DistributionFactory distributionFactoryIF;
        try (PrintWriter printWriter = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream("distribution.txt"),
                        Charset.forName("UTF-8").newEncoder()));
             InputStream inputStream = Launcher.class.getResourceAsStream("/parameters/parameters.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            distributionFactoryIF = new DistributionFactory(properties, printWriter);
            IDistribution iDistribution = distributionFactoryIF.createDistribution(Dialog.select());
            iDistribution.distribute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
