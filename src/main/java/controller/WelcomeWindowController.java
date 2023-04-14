package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Data;
import model.Members;

import java.util.ArrayList;

public class WelcomeWindowController {

    static public void login(TextField userTextField, PasswordField pwBox, Text actiontarget) {
        String username = userTextField.getText();
        String password = pwBox.getText();

        Members member = new Members();

        if (!member.verifyUser(username, password)) {
            actiontarget.setFill(Color.RED);
            actiontarget.setText("Incorrect username or password");
        } else {
            actiontarget.setFill(Color.GREEN);
            actiontarget.setText("Login successful");
            member = new Members(member.findUser(username, password));
            member.printMember();
        }
    }

    static public void signUp() {
        // Création de la nouvelle fenêtre d'inscription
        Stage signUpStage = new Stage();
        signUpStage.setTitle("Sign Up");

        // Création du formulaire d'inscription
        GridPane signUpGrid = new GridPane();
        signUpGrid.setAlignment(Pos.CENTER);
        signUpGrid.setHgap(10);
        signUpGrid.setVgap(10);
        signUpGrid.setPadding(new Insets(25, 25, 25, 25));

        // Ajout du champ d'identifiant
        Label signUpUserName = new Label("* User Name:");
        signUpGrid.add(signUpUserName, 0, 1);
        TextField signUpUserTextField = new TextField();
        signUpGrid.add(signUpUserTextField, 1, 1);

        // Ajout du champ d'email
        Label signUpEmail = new Label("* Email:");
        signUpGrid.add(signUpEmail, 0, 2);
        TextField signUpEmailTextField = new TextField();
        signUpGrid.add(signUpEmailTextField, 1, 2);

        // Ajout du champ de mot de passe
        Label signUpPw = new Label("* Password:");
        signUpGrid.add(signUpPw, 0, 3);
        PasswordField signUpPwBox = new PasswordField();
        signUpGrid.add(signUpPwBox, 1, 3);

        // Ajout du champ de pays
        Label signUpCountry = new Label("Country:");
        signUpGrid.add(signUpCountry, 0, 4);
        ComboBox<String> comboBoxCountry = new ComboBox<>();
        comboBoxCountry.getItems().addAll("France", "Germany", "Italy", "Spain", "United Kingdom");
        comboBoxCountry.getSelectionModel().selectFirst();
        signUpGrid.add(comboBoxCountry, 1, 4);


        // Ajout du genre (Male, Female, Other)
        Label signUpGender = new Label("Gender:");
        signUpGrid.add(signUpGender, 0, 5);
        ComboBox<String> comboBoxGender = new ComboBox<>();
        comboBoxGender.getItems().addAll("Male", "Female", "Other");
        comboBoxGender.getSelectionModel().selectFirst();
        signUpGrid.add(comboBoxGender, 1, 5);

        // Numero de telephone
        Label signUpPhone = new Label("Phone:");
        signUpGrid.add(signUpPhone, 0, 6);
        TextField signUpPhoneTextField = new TextField();
        signUpGrid.add(signUpPhoneTextField, 1, 6);

        // Ajout du bouton d'inscription
        Button signUpBtn = new Button("Sign up");
        HBox signUpHbBtn = new HBox(10);
        signUpHbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        signUpHbBtn.getChildren().add(signUpBtn);
        signUpGrid.add(signUpHbBtn, 1, 7);

        Label signUpRequired = new Label("* Required");
        signUpRequired.setTextFill(Color.RED);
        signUpRequired.setAlignment(Pos.BOTTOM_RIGHT);
        signUpRequired.setFont(Font.font("Arial", 10));
        signUpGrid.add(signUpRequired, 0, 7);


        Members member = new Members();
        final Text actiontarget = new Text();
        signUpGrid.add(actiontarget, 1, 8);

        // Gestionnaire d'événements du bouton d'inscription
        signUpBtn.setOnAction(e2 -> {
            actiontarget.setText("");
            Data data = new Data();
            ArrayList<Members> members = data.getAllMembers();

            String signUpUsername = signUpUserTextField.getText();
            if (signUpUsername.isEmpty()) {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Username is required");
                return;
            }
            String signUpPassword = signUpPwBox.getText();
            if (signUpPassword.isEmpty()) {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Password is required");
                return;
            }
            String signUpEmailStr = signUpEmailTextField.getText();
            if (signUpEmailStr.isEmpty()) {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Email is required");
                return;
            }
            for (Members member1 : members){
                if (member1.getUsername().equals(signUpUsername)) {
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Username already exists");
                    return;
                }
                if (member1.getEmail().equals(signUpEmailStr)) {
                    actiontarget.setFill(Color.RED);
                    actiontarget.setText("Email already exists");
                    return;
                }
            }
            if (signUpPassword.length() < 8) {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Password must be at least 8 characters");
                return;
            }

            String selectedGender = comboBoxGender.getValue();
            String selectedCountry = comboBoxCountry.getValue();
            String signUpPhoneStr = signUpPhoneTextField.getText();

            System.out.println("Username: " + signUpUsername);
            System.out.println("Password: " + signUpPassword);
            System.out.println("Email: " + signUpEmailStr);
            System.out.println("Country: " + selectedCountry);
            System.out.println("Gender: " + selectedGender);
            System.out.println("Phone: " + signUpPhoneStr);

            // Insérer ici le code pour l'ajout d'un nouvel utilisateur dans la base de données ou dans un fichier
            member.create_account(signUpUsername, signUpEmailStr, signUpPassword, selectedCountry, selectedGender, signUpPhoneStr);
            // Fermeture de la fenêtre d'inscription après l'enregistrement
            signUpStage.close();
        });

        // Création de la scène de la fenêtre d'inscription
        Scene signUpScene = new Scene(signUpGrid, 400, 300);
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }
}
