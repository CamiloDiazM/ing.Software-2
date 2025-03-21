package Model.co.edu.poli.ejemplo1.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Principal");


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Model/co/edu/poli/ejemplo1/Views/MainView.fxml"));
            Scene scene = new Scene(root, 150, 250);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
