package view;

import controller.MemberController;
import controller.WelcomeWindowController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Members;

public class MemberWindow {
    static public void memberWindow(Stage stage, Members member){
        stage.setTitle("Member Window");
        //todo: définir les cliques sur image

        Scene scene = new Scene(MemberController.gridPaneMember(stage, member), 1086, 724);
        stage.setScene(scene);
        stage.show();
    }
}
