// src/main/java/Model/co/edu/poli/ejemplo1/Controllers/CarritoController.java
package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class CarritoController {

    @FXML
    private RadioButton des;

    @FXML
    private TextField descuento;

    @FXML
    private RadioButton envgratis;

    @FXML
    private RadioButton punt;

    @FXML
    private TextField puntos;

    @FXML
    public void mostrarCarrito() {
        Carrito carrito = new CarritoBasico();

        if (des.isSelected()) {
            double descuentoValor = Double.parseDouble(descuento.getText()) / 100;
            carrito = new Descuentos(carrito, descuentoValor);
        }

        if (punt.isSelected()) {
            int puntosValor = Integer.parseInt(puntos.getText());
            carrito = new AcumulacionPuntos(carrito, puntosValor);
        }

        double precioBase = carrito.obtenerPrecio() + 15.0;
        double precioFinal = envgratis.isSelected() ? precioBase - 15.0 : precioBase;

        String descripcion = carrito.obtenerDescripcion();
        boolean envioGratis = envgratis.isSelected();
        int puntos = carrito.obtenerPuntos();

        StringBuilder sb = new StringBuilder();
        sb.append("Descripción del carrito:\n").append(descripcion).append("\n");
        sb.append("Precio total: $").append(precioFinal).append("\n");
        sb.append("Envío gratis: ").append(envioGratis ? "Sí" : "No").append("\n");
        sb.append("Puntos designados: ").append(puntos).append("\n");

        showAlert(sb.toString());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información del Carrito");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}