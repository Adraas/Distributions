package ru.wkn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ru.wkn.model.InformationStreamsFacade;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StreamerWindowController {

    @FXML
    private BarChart barChartProbabilities;
    @FXML
    private ListView listViewRandomVariable;
    @FXML
    private TextField textFieldSelectionSize;
    @FXML
    private TextField textFieldLambdaValue;
    @FXML
    private TextField textFieldMinWaitingTime;
    private InformationStreamsFacade informationStreamsFacade;

    @FXML
    private void clickOnMenuItemClose() {
        System.exit(0);
    }

    @FXML
    private void clickOnMenuItemAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Streamer-WKN");
        alert.setHeaderText("Информация о программе и авторе");
        alert.setContentText("Задача: Получить последовательность событий,\n"
                .concat("образующих информационный поток Пальма.\n")
                .concat("Автор: Пикалов Артем\n")
                .concat("Группа: 6302-090301D"));
        alert.show();
    }

    @FXML
    private void clickOnButtonGenerate() {
        if (!textFieldSelectionSize.getText().equals("")
                && !textFieldLambdaValue.getText().equals("")
                && !textFieldMinWaitingTime.getText().equals("")) {
            updateElementsContent();

            int selectionSize = Integer.parseInt(textFieldSelectionSize.getText());
            double lambdaValue = Integer.parseInt(textFieldLambdaValue.getText());
            double minWaitingTime = Integer.parseInt(textFieldMinWaitingTime.getText());

            informationStreamsFacade = new InformationStreamsFacade();
            informationStreamsFacade.initDistributor("exponential-distributor");
            informationStreamsFacade.initDistribution(getExponentialProperties(selectionSize, lambdaValue, minWaitingTime));
            informationStreamsFacade.initStream("palm-stream");

            drawOnBarChart();
            fillTheListView();
        }
    }

    private Properties getExponentialProperties(int selectionSize, double lambdaValue, double minWaitingTime) {
        InputStream inputStream = StreamerWindowController.class
                .getResourceAsStream("/stream-parameters/palm-stream.properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);

            properties.setProperty("selectionSize", String.valueOf(selectionSize));
            properties.setProperty("lambdaValue", String.valueOf(lambdaValue));
            properties.setProperty("minWaitingTime", String.valueOf(minWaitingTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void drawOnBarChart() {
        XYChart.Series<String, Integer> dataOfSeries = new XYChart.Series<>();
        dataOfSeries.setName("Частоты попадания в интервалы");
        int size = 0; // other value some waiting...

        for (int i = 0; i < size; i++) {
            // filling...
        }
        barChartProbabilities.getData().add(dataOfSeries);
    }

    private void fillTheListView() {
        double[] randomSample = null; // other value some waiting...
        ObservableList<Double> observableList = FXCollections.observableArrayList();

        for (double distributionOfRandomVariable : randomSample) {
            observableList.add(distributionOfRandomVariable);
        }
        listViewRandomVariable.setItems(observableList);
    }

    private void updateElementsContent() {
        barChartProbabilities.getData().clear();
        listViewRandomVariable.getItems().clear();
    }
}
