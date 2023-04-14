package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.*;
import model.Members;
import model.Users;
import model.hotels.Hotels;

import java.util.ArrayList;

public class ResearchView {
    static public void printResearch(Stage stage, Users user, Members member){
        stage.setTitle("ECE-BOOKING");
        ArrayList<Hotels> hotels = user.getTempSearch().searchHotel();

        VBox pageScrolling = new VBox(30);
        pageScrolling.setPadding(new Insets(20, 20, 20, 20));
        pageScrolling.setAlignment(Pos.CENTER);
        pageScrolling.setStyle("-fx-background-color: #043580; -fx-border-color: #043580;");


        Text scenetitle = new Text("Search results for " + user.getTempSearch().getDestination());
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        scenetitle.setFill(Color.WHITE);

        for (Hotels hotel : hotels) {
            GridPane grid = new GridPane();
            ArrayList<String> photos = hotel.getPhotos();

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setHalignment(HPos.CENTER);
            column1.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(column1);

            ImageView imageView = new ImageView(photos.get(0));
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            GridPane.setRowSpan(imageView, 3);
            grid.add(imageView, 0, 0, 1, 3);

            // Créer les deux colonnes suivantes pour le titre
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setHalignment(HPos.LEFT);
            col2.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(col2);

            ColumnConstraints col3 = new ColumnConstraints();
            col3.setHalignment(HPos.LEFT);
            col3.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(col3);

            Background backgroundTitle = new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY));
            grid.setBackground(backgroundTitle);

            // Ajouter le titre sur les deux dernières cases de la première ligne
            Label title = new Label(hotel.getName());
            title.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold;");
            grid.add(title, 1, 0, 2, 1);

            // Ajouter la description sur les deux dernières cases de la deuxième ligne
            Label description = new Label(hotel.getDescription());
            description.setWrapText(true);
            grid.add(description, 1, 1, 2, 1);

            // Ajouter le pays sur la deuxième case de la dernière ligne
            Label country = new Label(hotel.getAdress().toString());
            grid.add(country, 1, 2);

            // Ajouter la catégorie sur la dernière case de la dernière ligne
            Label category = new Label("Category: " + hotel.getCategory());
            grid.add(category, 2, 2);

            // Ajuster les marges et les espacements
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10));

            grid.getStyleClass().add("grid-hotel-component");
            grid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

            pageScrolling.getChildren().add(grid);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pageScrolling);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportWidth(1086);
        scrollPane.setPrefViewportHeight(724);

        VBox layout = new VBox(30);
        layout.setPadding(new Insets(25));
        layout.getChildren().addAll(scenetitle, scrollPane);

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #043580");
        root.getChildren().addAll(layout);
        Scene scene = new Scene(root, 1086, 724);
        stage.setScene(scene);
        stage.show();
    }
}
