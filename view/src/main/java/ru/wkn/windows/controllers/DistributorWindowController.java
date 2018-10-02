package ru.wkn.windows.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class DistributorWindowController {

    @FXML
    private BarChart barChartDistributions;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private Button buttonCheckByQualityControl;
    @FXML
    private TextField textFieldSizeOfSelection;
    @FXML
    private Button buttonGenerate;

    @FXML
    private void clickOnMenuItemClose() {
        System.exit(0);
    }

    @FXML
    private void clickOnButtonCheckByQualityControl() {
    }

    @FXML
    private void clickOnButtonGenerate() {
    }
}
