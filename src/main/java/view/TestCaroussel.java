package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestCaroussel extends Application {

    private int currentIndex = 0;

    private ImageView imageView;
    private Image[] images = new Image[] {
            new Image("https://picsum.photos/id/100/400/300"),
            new Image("https://picsum.photos/id/200/400/300"),
            new Image("https://picsum.photos/id/300/400/300")
    };

    @Override
    public void start(Stage primaryStage) {
        imageView = new ImageView(images[currentIndex]);
        imageView.setFitWidth(600);
        imageView.setFitHeight(450);

        Button prevButton = new Button("<");
        prevButton.setOnAction(e -> {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = images.length - 1;
            }
            imageView.setImage(images[currentIndex]);
        });

        Button nextButton = new Button(">");
        nextButton.setOnAction(e -> {
            currentIndex++;
            if (currentIndex >= images.length) {
                currentIndex = 0;
            }
            imageView.setImage(images[currentIndex]);
        });

        HBox buttonsBox = new HBox(prevButton, nextButton);
        buttonsBox.setAlignment(Pos.CENTER);

        StackPane imagePane = new StackPane();
        imagePane.getChildren().add(imageView);
        imagePane.getChildren().add(buttonsBox);
        StackPane.setAlignment(buttonsBox, Pos.CENTER_RIGHT);

        VBox mainBox = new VBox(20, imagePane);
        mainBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainBox);
        primaryStage.setScene(scene);
        primaryStage.show();

        // afficher l'image sélectionnée en grand lorsqu'on clique dessus
        imageView.setOnMouseClicked(e -> {
            ImageView fullImageView = new ImageView(images[currentIndex]);
            fullImageView.setFitWidth(800);
            fullImageView.setFitHeight(600);

            VBox fullImageBox = new VBox(fullImageView);
            fullImageBox.setAlignment(Pos.CENTER);
            Scene fullImageScene = new Scene(fullImageBox);

            Stage fullImageStage = new Stage();
            fullImageStage.setScene(fullImageScene);
            fullImageStage.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

