package ru.wkn;

import ru.wkn.distributions.PoissonDistribution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Launcher {

    public static void main(String[] args) {
        File file = new File("new_data34.txt");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            PoissonDistribution poissonDistribution = new PoissonDistribution(printWriter);
            poissonDistribution.distribute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
