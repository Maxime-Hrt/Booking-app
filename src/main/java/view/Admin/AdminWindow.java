package view.Admin;

import controller.Admin.AddHotelButton;
import controller.Admin.EraseHotelButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Members;

public class AdminWindow {
    static public void adminWindow(Stage adminStage, Members member){

        if (adminStage == null){
            adminStage = new Stage();
        }


        Scene scene = new Scene(new VBox(), 650, 400);

        VBox vBox = new VBox(40);
        vBox.setAlignment(Pos.CENTER);
        vBox.getStyleClass().add("background-blue");
        vBox.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

        Button addHotel = new Button("Add Hotel");
        Button modifyHotel = new Button("Modify Hotel");
        Button eraseHotel = new Button("Erase Hotel");
        Button watchData = new Button("Watch Data");

        Stage finalAdminStage = adminStage;
        addHotel.setOnAction(actionEvent -> {
            AddHotelButton.addHotelButton(finalAdminStage, member);
        });
        eraseHotel.setOnAction(actionEvent -> {
            EraseHotelButton.eraseHotelButton(finalAdminStage, member);
        });

        vBox.getChildren().addAll(addHotel, modifyHotel, eraseHotel, watchData);
        scene.setRoot(vBox);
        adminStage.setScene(scene);
        adminStage.show();
    }
}
