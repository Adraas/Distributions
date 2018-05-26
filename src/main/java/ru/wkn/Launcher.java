package ru.wkn;

public class Launcher {

    public static void main(String[] args) {
        Core core = new Core();
        core.writeDistributionInFile("poisson.txt", "/parameters/poisson.properties");
        core.writeDistributionInFile("uniform.txt", "/parameters/uniform.properties");
    }
}
