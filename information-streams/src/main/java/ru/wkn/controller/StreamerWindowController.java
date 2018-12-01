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
            double lambdaValue = Double.parseDouble(textFieldLambdaValue.getText());
            double minWaitingTime = Double.parseDouble(textFieldMinWaitingTime.getText());
            InformationStreamsFacade informationStreamsFacade = new InformationStreamsFacade();

            informationStreamsFacade.initDistributor("exponential-distributor");
            informationStreamsFacade.initDistribution(getExponentialProperties(selectionSize, lambdaValue));
            informationStreamsFacade.initStream("palm-stream");

            double[] timeIntervals = informationStreamsFacade.getStream().initTimeIntervals(
                    informationStreamsFacade.getDistribution(), minWaitingTime);
            drawOnBarChart(timeIntervals);
            fillTheListView(timeIntervals);
        }
    }

    private Properties getExponentialProperties(int selectionSize, double lambdaValue) {
        InputStream inputStream = StreamerWindowController.class
                .getResourceAsStream("/stream-parameters/palm-stream.properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);

            properties.setProperty("selectionSize", String.valueOf(selectionSize));
            properties.setProperty("lambdaValue", String.valueOf(lambdaValue));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void drawOnBarChart(double[] timeIntervals) {
        XYChart.Series<String, Double> dataOfSeries = new XYChart.Series<>();
        dataOfSeries.setName("Интервалы времени между событиями");
        int size = timeIntervals.length;

        for (int i = 0; i < size; i++) {
            dataOfSeries.getData().add(new XYChart.Data<>(String.valueOf(i), timeIntervals[i]));
        }
        barChartProbabilities.getData().add(dataOfSeries);
    }

    private void fillTheListView(double[] timeIntervals) {
        ObservableList<Double> observableList = FXCollections.observableArrayList();

        for (double timeInterval : timeIntervals) {
            observableList.add(timeInterval);
        }
        listViewRandomVariable.setItems(observableList);
    }

    private void updateElementsContent() {
        barChartProbabilities.getData().clear();
        listViewRandomVariable.getItems().clear();
    }
}
