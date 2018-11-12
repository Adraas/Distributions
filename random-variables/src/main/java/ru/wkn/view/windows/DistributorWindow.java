package ru.wkn.view.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.wkn.view.Window;

import java.io.IOException;

public class DistributorWindow extends Window {

    public void windowInitialization(String windowTitle, int windowWidth, int windowHeight) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/distributor-window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane, windowWidth, windowHeight));
        stage.setResizable(false);
        stage.setTitle(windowTitle);
        stage.show();
    }
}
