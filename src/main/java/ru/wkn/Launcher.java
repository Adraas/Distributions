package ru.wkn;

import ru.wkn.distributions.PoissonDistribution;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class Launcher {

    public static void main(String[] args) {
        try (PrintWriter printWriter = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream("new_data34.txt"),
                        Charset.forName("UTF-8").newEncoder()));
             InputStream inputStream = Launcher.class.getResourceAsStream("/parameters/parameters.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            PoissonDistribution poissonDistribution = new PoissonDistribution(properties, printWriter);
            poissonDistribution.distribute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
