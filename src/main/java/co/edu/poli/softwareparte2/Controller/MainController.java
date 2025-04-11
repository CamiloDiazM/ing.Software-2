package co.edu.poli.softwareparte2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button btnFlyWeight;

    @FXML
    private void initialize() {
        btnFlyWeight.setOnAction(e -> mostrarFlyWeightMenu());
        }

    private void mostrarFlyWeightMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/softwareparte2/View/FlyWeightView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu de Proveedores");
            stage.setScene(new Scene(root, 300, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
