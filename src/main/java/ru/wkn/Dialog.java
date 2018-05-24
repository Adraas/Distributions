package ru.wkn;

import java.util.Scanner;

public class Dialog {

    public static String select() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:\n1. 'poisson'\n2. 'uniform'");
        String choice = scanner.nextLine();
        switch (choice) {
            case "poisson":
                break;
            case "uniform":
                break;
            default: {
                System.out.println("This option does not exist at the moment. Repeat please.\n");
                choice = select();
            }
        }
        return choice;
    }
}
