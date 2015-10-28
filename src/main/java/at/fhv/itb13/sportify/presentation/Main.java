package at.fhv.itb13.sportify.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFrame.fxml"));
        primaryStage.setTitle("Sportify");
        primaryStage.setScene(new Scene(root, 1500, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
