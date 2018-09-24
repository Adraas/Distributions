package ru.wkn.distributions;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class PoissonDistribution extends Distribution {

    private Properties properties;
    private Writer writer;

    public PoissonDistribution(Properties properties, Writer writer) {
        super(properties, writer);
        this.properties = properties;
        this.writer = writer;
    }

    @Override
    public void distribute() throws IOException {
        StringBuilder data = new StringBuilder();
        double alpha = 0;
        int size = Integer.parseInt(properties.getProperty("size"));
        for (int i = 0; i < size; i++) {
            double lambda = Double.parseDouble(properties.getProperty("lambda"));
            double exp = Math.exp(-lambda);
            double s = exp;
            int k = 0;
            alpha = super.getRandomValue(alpha);
            while (s < alpha) {
                k++;
                exp *= lambda / k;
                s += exp;
            }
            data.append(String.valueOf(k)).append("\r\n");
        }
        writer.write(data.toString());
        writer.flush();
    }
}
