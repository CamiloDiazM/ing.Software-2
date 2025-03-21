package Model.co.edu.poli.ejemplo1.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button Clientebtn;

    @FXML
    private Button Productobtn;

    @FXML
    private Button Salirbtn;

    @FXML
    private void initialize() {
        Clientebtn.setOnAction(e -> mostrarClienteMenu());
        Productobtn.setOnAction(e -> mostrarProductoMenu());
        Salirbtn.setOnAction(e -> salir());
    }

    private void mostrarProductoMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Model/co/edu/poli/ejemplo1/Views/ProductoView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu de Productos");
            stage.setScene(new Scene(root, 300, 500));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarClienteMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Model/co/edu/poli/ejemplo1/Views/ClienteView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu de Clientes");
            stage.setScene(new Scene(root, 300, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salir() {
        Stage stage = (Stage) Salirbtn.getScene().getWindow();
        stage.close();
    }
}
