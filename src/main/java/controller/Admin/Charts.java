package controller.Admin;

import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Data;
import model.Members;
import org.bson.Document;
import view.Admin.AdminWindow;

public class Charts {

    static public void barCharts(Stage stage, Members member) {

        VBox vBox = new VBox(40);
        Button backBtn = new Button("Back");
        backBtn.setOnAction(actionEvent -> {
            AdminWindow.adminWindow(stage, member);
        });


        Data data = new Data();
        List<Document> userList = data.getUserDao().getAllUsers();
        ArrayList<String> countries = new ArrayList<>();
        //Stage stageChart = new Stage();

        for (Document user : userList) {
            if (user.containsKey("country")) {
                countries.add(user.getString("country"));
            }
        }

        // Comptage des occurrences de chaque pays
        Map<String, Integer> occurrences = new HashMap<>();
        for (String country : countries) {
            occurrences.put(country, occurrences.getOrDefault(country, 0) + 1);
        }

        // Tri des pays par nombre d'occurrences décroissant
        List<Map.Entry<String, Integer>> sortedData = new ArrayList<>(occurrences.entrySet());
        sortedData.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Création des données du diagramme
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : sortedData) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Définition des axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        // Création du diagramme
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.getData().add(series);
        chart.setTitle("Country Repartition");

        vBox.getChildren().addAll(chart, backBtn);

        // Création de la scène
        Scene scene = new Scene(vBox, 650, 400);
        stage.setScene(scene);
        stage.show();
    }
}


    /*
    @Override
    public void start (Stage stage){
        Data data = new Data();
        List<Document> userList = data.getUserDao().getAllUsers();
        ArrayList<String> countries = new ArrayList<>();
        //Stage stageChart = new Stage();

        for (Document user : userList) {
            if (user.containsKey("country")) {
                countries.add(user.getString("country"));
            }
        }

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        Iterator<String> it = countries.iterator();
        int index = 0;
        while (it.hasNext()) {
            pieDataset.setValue(it.next(), index + 1);
        }

        JFreeChart chart = ChartFactory.createPieChart("User Country", pieDataset, true, true, false);
        stage.setTitle("Charts");

        VBox vbox = new VBox(new ChartViewer(chart));
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/

