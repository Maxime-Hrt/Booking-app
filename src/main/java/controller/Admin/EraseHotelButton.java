package controller.Admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Data;
import model.Members;
import model.hotels.Hotels;
import view.Admin.AdminWindow;

import java.util.ArrayList;

public class EraseHotelButton {
    static public void eraseHotelButton(Stage stage, Members member) {
        Scene scene = new Scene(new VBox(), 600, 400);
        VBox hotelList = new VBox(10);
        Data data = new Data();
        ArrayList<Hotels> hotels = data.getAllHotels();
        Button backBtn = new Button("Back");
        hotelList.getChildren().add(backBtn);
        backBtn.setOnAction(actionEvent -> {
            AdminWindow.adminWindow(stage, member);
        });
        for (Hotels hotel : hotels){
            Button hotelBtn = new Button(hotel.getName());
            hotelBtn.setOnAction(actionEvent -> {
                EraseButton(stage, hotel, member, data);
            });
            hotelList.getChildren().add(hotelBtn);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hotelList);
        scene.setRoot(scrollPane);
        stage.setScene(scene);
        stage.show();
    }

    static public void EraseButton(Stage stage, Hotels hotel, Members member, Data data) {
        Scene sceneErasePopUP = new Scene(new VBox(), 300, 200);
        Stage erasePopUPStage = new Stage();
        VBox erasePopUP = new VBox(10);
        Label eraseLabel = new Label("Are you sure you want to erase this hotel?");
        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        yesBtn.setOnAction(actionEvent -> {
            erasePopUPStage.close();
            data.getAccommodationsDao().deleteAccommodationName(hotel.getName());
            eraseHotelButton(stage, member);
        });
        noBtn.setOnAction(actionEvent -> {
            erasePopUPStage.close();
            eraseHotelButton(stage, member);
        });
        erasePopUP.getChildren().addAll(eraseLabel, yesBtn, noBtn);
        sceneErasePopUP.setRoot(erasePopUP);
        erasePopUPStage.setScene(sceneErasePopUP);
        erasePopUPStage.show();
    }
}
