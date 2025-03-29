package Model.co.edu.poli.ejemplo1.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PagoController {

    @FXML
    private Button btnNequi;

    @FXML
    private Button btnPaypal;

    @FXML
    private void initialize() {
        btnNequi.setOnAction(e -> mostrarNequiMenu());
        btnPaypal.setOnAction(e -> mostrarPaypalMenu());
    }

    private void mostrarNequiMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Model/co/edu/poli/ejemplo1/Views/PagoNequi.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pago con Nequi");
            stage.setScene(new Scene(root, 348, 224));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void mostrarPaypalMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Model/co/edu/poli/ejemplo1/Views/pagoPaypal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pago con Paypal");
            stage.setScene(new Scene(root, 348, 224));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}