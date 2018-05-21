package ru.wkn;

import ru.wkn.distributions.PoissonDistribution;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Launcher {

    public static void main(String[] args) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("new_data34.txt"),
                        "UTF8"))) {
            PoissonDistribution poissonDistribution = new PoissonDistribution(bufferedWriter);
            poissonDistribution.distribute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
