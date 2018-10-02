package ru.wkn;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.wkn.windows.DistributorWindow;

import java.io.IOException;

public class WindowLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        new DistributorWindow().windowInitialization("Distributor", 745, 401);
    }
}
