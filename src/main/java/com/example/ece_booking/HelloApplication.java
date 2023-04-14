package com.example.ece_booking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ECE-BOOKING!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

/*
public class WelcomeWindow extends Application {

    @Override
    public void start(Stage stage) {

        Image backgroundImage = new Image("src/main/resources/Assets/WelcomePicture.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stage.heightProperty());

        // Création de la grille de la page de bienvenue
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        gridPane.getChildren().add(backgroundImageView);

        // Création du texte de titre
        Text welcomeText = new Text("Welcome!");
        welcomeText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        welcomeText.setFill(Color.WHITE);
        gridPane.add(welcomeText, 0, 0, 2, 1);

        // Création du bouton Register
        Button registerBtn = new Button("Register");
        HBox hbRegisterBtn = new HBox(10);
        hbRegisterBtn.setAlignment(Pos.CENTER);
        hbRegisterBtn.getChildren().add(registerBtn);
        gridPane.add(hbRegisterBtn, 0, 2);

        // Gestionnaire d'événements du bouton Register
        registerBtn.setOnAction(e -> {
            // Insérer ici le code pour ouvrir la fenêtre d'inscription
        });

        // Création du bouton "Continue as a guest"
        Button guestBtn = new Button("Continue as a guest");
        HBox hbGuestBtn = new HBox(10);
        hbGuestBtn.setAlignment(Pos.CENTER);
        hbGuestBtn.getChildren().add(guestBtn);
        gridPane.add(hbGuestBtn, 1, 2);

        // Gestionnaire d'événements du bouton "Continue as a guest"
        guestBtn.setOnAction(e -> {
            // Insérer ici le code pour continuer en tant qu'invité
        });

        // Création de la scène de la page de bienvenue
        Scene scene = new Scene(gridPane, 800, 600);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/