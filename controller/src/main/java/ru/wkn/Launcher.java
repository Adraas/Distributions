package ru.wkn;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        Controller controller = new Controller();
        String pathname = "/Distributions/buffer.txt";
        try (FileReader fileReader = new FileReader(new File(pathname));
             FileWriter fileWriter = new FileWriter(new File(pathname))) {
            controller.listenRequestAndSendResponse(fileReader, fileWriter);
        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
