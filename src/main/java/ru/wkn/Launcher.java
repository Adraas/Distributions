package ru.wkn;

import ru.wkn.distributions.PoissonDistribution;

import java.io.*;

public class Launcher {

    public static void main(String[] args) {
        File file = new File("new_data34.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file.getName()), "UTF-8"))) {
            PoissonDistribution poissonDistribution = new PoissonDistribution(bufferedWriter);
            poissonDistribution.distribute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
