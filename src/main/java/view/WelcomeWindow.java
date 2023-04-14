package view;

import controller.WelcomeWindowController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class WelcomeWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("ECE-BOOKING");

        // create the ImageView
        ImageView imageView = new ImageView("file:src/main/resources/Assets/WelcomePicture.jpg");
        imageView.setFitWidth(1086);
        imageView.setFitHeight(724);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, -50, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        scenetitle.setFill(Color.WHITE);
        grid.add(scenetitle, 0, 0, 2, 1);

        // Ajout du champ d'identifiant
        Label userName = new Label("User Name:");
        userName.setTextFill(Color.WHITE);
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        // Ajout du champ de mot de passe
        Label pw = new Label("Password:");
        pw.setTextFill(Color.WHITE);
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        // Ajout du bouton de connexion
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 4);

        // Ajout du bouton d'inscription
        Button signUpButton = new Button("Sign up");
        HBox signUpHBox = new HBox(10);
        signUpHBox.setAlignment(Pos.BOTTOM_LEFT);
        signUpHBox.getChildren().add(signUpButton);
        grid.add(signUpHBox, 0, 4);

        //Ajout du bouton invité
        Button guestButton = new Button("Continue as guest");
        HBox guestHBox = new HBox(10);
        guestHBox.setAlignment(Pos.CENTER);
        guestHBox.getChildren().add(guestButton);
        grid.add(guestHBox, 1, 4);

        // Ajout du message d'erreur
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        guestButton.setOnAction(e -> {

        });

        // Gestionnaire d'événements du bouton de connexion
        btn.setOnAction(e -> WelcomeWindowController.login(userTextField, pwBox, actiontarget));

        // Gestionnaire d'événements du bouton d'inscription
        signUpButton.setOnAction(e -> WelcomeWindowController.signUp());

        // create the StackPane
        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, grid);

        // Création de la scène de la page de connexion
        Scene scene = new Scene(root, 1086, 724);
        stage.setScene(scene);
        stage.show();

        /*
        // create the VBox layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(registerButton, loginButton, guestButton);
        layout.setAlignment(Pos.CENTER);
        */
    }

    public static void main(String[] args) {
        launch(args);
    }
}
