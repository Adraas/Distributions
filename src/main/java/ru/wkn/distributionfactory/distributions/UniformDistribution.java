package ru.wkn.distributionfactory.distributions;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

public class UniformDistribution extends Distribution {

    private Properties properties;
    private Writer writer;

    public UniformDistribution(Properties properties, Writer writer) {
        super(properties, writer);
        this.properties = properties;
        this.writer = writer;
    }

    @Override
    public void distribute() throws IOException {
        StringBuilder data = new StringBuilder();
        double n = 0;
        int size = Integer.parseInt(properties.getProperty("size"));
        for (int i=0; i < size; i++) {
            n = super.getRandomValue(n);
            n = n * 0.0052127 + 2.241772;
            n = new BigDecimal(n).setScale(5, RoundingMode.UP).doubleValue();
            data.append(n).append("\r\n");
        }
        writer.write(data.toString());
        writer.flush();
    }
}
