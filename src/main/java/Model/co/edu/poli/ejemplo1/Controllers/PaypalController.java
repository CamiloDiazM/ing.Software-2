package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Model.PaypalAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaypalController {

    @FXML
    private Button btnPago;

    @FXML
    private Label lblPago;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtMonto;

    @FXML
    private void initialize() {
        lblPago.setText("");
        btnPago.setOnAction(e -> realizarPago());
    }

    private void realizarPago() {
        String correo = txtCorreo.getText();
        String monto = txtMonto.getText();
        if (!validarCorreo(correo)) {
            lblPago.setText("Correo electrónico inválido.");
            return;
        }

        PaypalAdapter paypalAdapter = new PaypalAdapter(correo);
        String resultado = paypalAdapter.realizarPago(Double.parseDouble(monto));
        lblPago.setText(resultado);
    }

    private boolean validarCorreo(String correo) {
        return correo.contains("@") && correo.contains(".com");
    }

}
