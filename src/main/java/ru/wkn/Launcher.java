package ru.wkn;

import ru.wkn.distributions.PoissonDistribution;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class Launcher {

    public static void main(String[] args) {
        try (PrintWriter printWriter = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream("new_data34.txt"),
                        Charset.forName("UTF-8").newEncoder()))) {
            PoissonDistribution poissonDistribution = new PoissonDistribution(printWriter);
            poissonDistribution.distribute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
