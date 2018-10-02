package ru.wkn.windows.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class DistributorWindowController {

    @FXML
    private BarChart barChartDistributions;
    @FXML
    private ListView listViewRandomVariable;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private Button buttonCheckByQualityControl;
    @FXML
    private TextField textFieldSizeOfSelection;
    @FXML
    private TextField textFieldParameterN;

    @FXML
    private void clickOnMenuItemClose() {
        System.exit(0);
    }

    @FXML
    private void clickOnMenuItemAbout() {
    }

    @FXML
    private void clickOnButtonGenerate() {
        if (!textFieldSizeOfSelection.getText().equals("") && !textFieldParameterN.getText().equals("")) {
            updateElementsContent();
            int sizeOfSelection = Integer.valueOf(textFieldSizeOfSelection.getText());
            int parameterN = Integer.valueOf(textFieldParameterN.getText());
            // some instructions
            drawOnBarChart();
            fillTheListView();
            buttonCheckByQualityControl.setDisable(false);
        }
    }

    @FXML
    private void clickOnButtonCheckByQualityControl() {
        if (!textFieldParameterN.getText().equals("")) {
            // some instructions
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Контроль качества");
            alert.setContentText("Значение проверки по критерию Пирсона для заданной СВ: ");
            alert.getButtonTypes().add(ButtonType.OK);
            alert.show();
        }
    }

    private void drawOnBarChart() {
        double[] distributionOfRandomVariables = null;
        // some instructions
        XYChart.Series<String, Double> dataOfSeries = new XYChart.Series<>();
        dataOfSeries.setName("Случайные величины");
        int selectionSize = distributionOfRandomVariables.length;
        for (int indexOfRandomVariable = 0; indexOfRandomVariable < selectionSize; indexOfRandomVariable++) {
            dataOfSeries.getData().add(new XYChart.Data<>(String.valueOf(indexOfRandomVariable), distributionOfRandomVariables[indexOfRandomVariable]));
        }
        barChartDistributions.getData().add(dataOfSeries);
    }

    private void fillTheListView() {
        double[] distributionOfRandomVariables = null;
        // some instructions
        ObservableList<Double> observableList = FXCollections.observableArrayList();
        for (double distributionOfRandomVariable : distributionOfRandomVariables) {
            observableList.add(distributionOfRandomVariable);
        }
        listViewRandomVariable.setItems(observableList);
    }

    private void updateElementsContent() {
        barChartDistributions.getData().clear();
        listViewRandomVariable.getItems().clear();
    }
}
