package ru.wkn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import ru.wkn.model.DistributorFacade;
import ru.wkn.utils.QualityControl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DistributorWindowController {

    @FXML
    private BarChart barChartDistributions;
    @FXML
    private ListView listViewRandomVariable;
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
    @FXML
    private Button buttonQualityControl;
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

            int selectionSize = Integer.parseInt(textFieldSelectionSize.getText());
            int valueRange = Integer.parseInt(textFieldValueRange.getText());
            int quantityOfIntervals = Integer.parseInt(textFieldQuantityOfIntervals.getText());
            double probability = sliderProbability.getValue();
            Properties properties = getBinomialProperties(selectionSize, valueRange, probability);

            distributorFacade = new DistributorFacade();
            distributorFacade.initDistributor("binomial-distributor");
            distributorFacade.initDistribution(properties);
            distributorFacade.initIntervals(quantityOfIntervals);

            drawOnBarChart();
            fillTheListView();
            textFieldThresholdValue.setDisable(false);
            buttonQualityControl.setDisable(false);
            properties.clear();
        }
    }

    @FXML
    private void clickOnButtonCheckByQualityControl() {
        if (!textFieldThresholdValue.getText().equals("")) {
            double thresholdValue = Double.valueOf(textFieldThresholdValue.getText());
            boolean result = QualityControl
                    .isImplementationBelongsToCurrentDistribution(
                            distributorFacade.getIntervals(),
                            distributorFacade.getDistributor().theoreticalProbabilities(),
                            thresholdValue);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Distributor-WKN");
            alert.setHeaderText("Контроль качества");
            alert.setContentText("Значение проверки по критерию Пирсона для заданной СВ: " + result);
            alert.show();
        }
    }

    private Properties getBinomialProperties(int selectionSize, int valueRange, double probability) {
        InputStream inputStream = DistributorWindowController.class
                .getResourceAsStream("/distribution-parameters/binomial.properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);

            properties.setProperty("selectionSize", String.valueOf(selectionSize));
            properties.setProperty("valueRange", String.valueOf(valueRange));
            properties.setProperty("probability", String.valueOf(probability));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void drawOnBarChart() {
        XYChart.Series<String, Integer> dataOfSeries = new XYChart.Series<>();
        dataOfSeries.setName("Частоты попадания в интервалы");
        int size = distributorFacade.getIntervals().length;

        for (int i = 0; i < size; i++) {
            dataOfSeries.getData().add(new XYChart.Data<>(
                    String.valueOf(i),
                    distributorFacade.getIntervals()[i].countOfIntervalValues()));
        }
        barChartDistributions.getData().add(dataOfSeries);
    }

    private void fillTheListView() {
        double[] randomSample = distributorFacade.getDistribution().getRandomSample();
        ObservableList<Double> observableList = FXCollections.observableArrayList();

        for (double distributionOfRandomVariable : randomSample) {
            observableList.add(distributionOfRandomVariable);
        }
        listViewRandomVariable.setItems(observableList);
    }

    private void updateElementsContent() {
        barChartDistributions.getData().clear();
        listViewRandomVariable.getItems().clear();
    }
}
