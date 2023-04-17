package controller.Admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Data;
import org.bson.Document;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Charts extends Application {
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
    }
}
