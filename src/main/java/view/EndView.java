package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import model.Data;
import model.Members;
import model.PastOrder;
import model.Users;
import org.bson.Document;

import java.util.ArrayList;

public class EndView {
    static public <Membres> void endView(Stage stage, Users user, String name, String email, GridPane gridMember) {

        //Todo: envoi de l'email
        //Todo: Lorsque je retourne au menu utilliser les datas de l'invité

        Stage endStage = new Stage();
        endStage.setTitle("Thank you for your purchase");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Label thx = new Label("Thank you for your purchase " + name + "!");
        thx.setFont(javafx.scene.text.Font.font("System", 30));
        thx.setWrapText(true);
        thx.setAlignment(Pos.CENTER);
        Label emailLabel = new Label("We sent you an email to " + email +
                " with all the details of the order made by **** **** **** " +
                user.getPaymentMethod().getLast4Numbers() +
                ", thank you for trusting us, we look forward to seeing you.\n"
        );
        emailLabel.setFont(javafx.scene.text.Font.font("System", 20));
        emailLabel.setWrapText(true);
        emailLabel.setAlignment(Pos.CENTER);

        Button okButton = new Button("Back to the menu");
        okButton.setOnAction(e -> {
            if(user instanceof Members){
                Data data = new Data();
                ArrayList<Document> allUsers = data.getAllUsers();
                ((Members) user).addOrder(
                        new PastOrder(user.getTempSearch().getDestination(),
                                user.getTempSearch().getDateOfArrival(),
                                user.getTempSearch().getDateOfDeparture())
                );

                System.out.println(((Members) user).getOrder_history());

                ((Members) user).upDate();
                MemberWindow.memberWindow(stage, (Members) user);
            }
            else{
                GuestWindow.guestWindow(stage, user);
            }
            endStage.close();
        });

        vBox.getChildren().addAll(thx, emailLabel, okButton);
        Scene scene = new Scene(vBox, 600, 400);
        endStage.setScene(scene);
        endStage.show();
    }
}
