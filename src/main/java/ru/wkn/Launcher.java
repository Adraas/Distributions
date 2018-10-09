package ru.wkn;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.wkn.view.windows.DistributorWindow;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new DistributorWindow().windowInitialization("Distributor", 745, 401);
    }
}
