package ru.wkn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.wkn.model.DistributorFacade;
import ru.wkn.utils.QualityControl;

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
    private GridPane gridPaneQualityControl;
    @FXML
    private Slider sliderProbability;
    @FXML
    private TextField textFieldSelectionSize;
    @FXML
    private TextField textFieldValueRange;
    @FXML
    private TextField textFieldQuantityOfIntervals;
    @FXML
    private TextField textFieldThresholdValue;
    private DistributorFacade distributorFacade;

    @FXML
    private void clickOnMenuItemClose() {
        System.exit(0);
    }

    @FXML
    private void clickOnMenuItemAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Distributor-WKN");
        alert.setHeaderText("Информация о программе и авторе");
        alert.setContentText("Задача: Сгенерировать биномиальное распределение\n"
                .concat("Автор: Пикалов Артем\n")
                .concat("Группа: 6302-090301D"));
        alert.show();
    }

    @FXML
    private void clickOnButtonGenerate() {
        if (!textFieldSelectionSize.getText().equals("")
                && !textFieldValueRange.getText().equals("")
                && !textFieldQuantityOfIntervals.getText().equals("")) {
            updateElementsContent();

            int selectionSize = Integer.valueOf(textFieldSelectionSize.getText());
            int valueRange = Integer.valueOf(textFieldValueRange.getText());
            int quantityOfIntervals = Integer.valueOf(textFieldQuantityOfIntervals.getText());
            double probability = sliderProbability.getValue();

            distributorFacade = new DistributorFacade();
            distributorFacade.getDistributor("binomial-distributor");
            distributorFacade.getBinomialDistribution(selectionSize, valueRange, probability);
            distributorFacade.getIntervals(quantityOfIntervals);

            drawOnBarChart();
            fillTheListView();
            gridPaneQualityControl.setDisable(false);
        }
    }

    @FXML
    private void clickOnButtonCheckByQualityControl() {
        if (!textFieldThresholdValue.getText().equals("")) {
            double thresholdValue = Double.valueOf(textFieldThresholdValue.getText());
            boolean result = QualityControl
                    .isImplementationBelongsToCurrentDistribution(
                            distributorFacade.getIntervals(0),
                            distributorFacade.getDistributor(null).theoreticalProbabilities(),
                            thresholdValue);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Distributor-WKN");
            alert.setHeaderText("Контроль качества");
            alert.setContentText("Значение проверки по критерию Пирсона для заданной СВ: " + result);
            alert.show();
        }
    }

    private void drawOnBarChart() {
        XYChart.Series<String, Integer> dataOfSeries = new XYChart.Series<>();
        dataOfSeries.setName("Частоты попадания в интервалы");

        for (int indexOfRandomVariable = 0; indexOfRandomVariable < distributorFacade.getIntervals(0).length; indexOfRandomVariable++) {
            dataOfSeries.getData().add(new XYChart.Data<>(
                    String.valueOf(indexOfRandomVariable),
                    distributorFacade.getIntervals(0)[indexOfRandomVariable].countOfIntervalValues()));
        }
        barChartDistributions.getData().add(dataOfSeries);
    }

    private void fillTheListView() {
        double[] distributionOfRandomVariables = distributorFacade.getBinomialDistribution(0, 0, 0).getRandomSample();
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
