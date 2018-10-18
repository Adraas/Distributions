package ru.wkn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.wkn.model.DistributorFacade;
import ru.wkn.model.distributions.Distribution;
import ru.wkn.model.distributions.Interval;
import ru.wkn.model.distributors.Distributor;
import ru.wkn.model.distributors.discrete.BinomialDistributor;
import ru.wkn.model.utils.QualityControl;

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
    private TextField textFieldSizeOfSelection;
    @FXML
    private TextField textFieldValueRange;
    @FXML
    private TextField textFieldQuantityOfIntervals;
    @FXML
    private TextField textFieldThresholdValue;
    private Distribution distribution;
    private Interval[] intervals;

    @FXML
    private void clickOnMenuItemClose() {
        System.exit(0);
    }

    @FXML
    private void clickOnMenuItemAbout() {
    }

    @FXML
    private void clickOnButtonGenerate() {
        if (!textFieldSizeOfSelection.getText().equals("")
                && !textFieldValueRange.getText().equals("")
                && !textFieldQuantityOfIntervals.getText().equals("")) {
            updateElementsContent();
            int sizeOfSelection = Integer.valueOf(textFieldSizeOfSelection.getText());
            int valueRange = Integer.valueOf(textFieldValueRange.getText());
            int quantityOfIntervals = Integer.valueOf(textFieldQuantityOfIntervals.getText());
            DistributorFacade distributorFacade = new DistributorFacade();
            Distributor distributor = distributorFacade
                    .getDistributor("binomial-distributor");
            distribution = ((BinomialDistributor) distributor)
                    .getDistribution(sizeOfSelection, valueRange, 0.5);
            intervals = ((BinomialDistributor) distributor).intervalsOfDistribution(distribution, quantityOfIntervals);
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
                    .isImplementationBelongsToDiscreteDistribution(intervals, distribution, thresholdValue);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Distributor-WKN");
            alert.setHeaderText("Контроль качества");
            alert.setContentText("Значение проверки по критерию Пирсона для заданной СВ: " + result);
            alert.show();
        }
    }

    private void drawOnBarChart() {
        XYChart.Series<String, Integer> dataOfSeries = new XYChart.Series<>();
        dataOfSeries.setName("Случайные величины");
        for (int indexOfRandomVariable = 0; indexOfRandomVariable < intervals.length; indexOfRandomVariable++) {
            dataOfSeries.getData().add(new XYChart.Data<>(String
                    .valueOf(indexOfRandomVariable),
                    intervals[indexOfRandomVariable].countOfIntervalValues()));
        }
        barChartDistributions.getData().add(dataOfSeries);
    }

    private void fillTheListView() {
        if (distribution != null) {
            double[] distributionOfRandomVariables = distribution.getImplementationsOfRandomVariable();
            ObservableList<Double> observableList = FXCollections.observableArrayList();
            for (double distributionOfRandomVariable : distributionOfRandomVariables) {
                observableList.add(distributionOfRandomVariable);
            }
            listViewRandomVariable.setItems(observableList);
        }
    }

    private void updateElementsContent() {
        barChartDistributions.getData().clear();
        listViewRandomVariable.getItems().clear();
    }
}
