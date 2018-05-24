package ru.wkn;

public class Launcher {

    public static void main(String[] args) {
        String propertyFilename = "/parameters/parameters.properties";
        Core core = new Core(propertyFilename);
        core.writeDistributionInFile("poisson.txt");
        core.writeDistributionInFile("uniform.txt");
    }
}
