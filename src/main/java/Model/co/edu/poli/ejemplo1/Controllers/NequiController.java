package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Model.Adapter.NequiAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NequiController {

    @FXML
    private Button btnPago;

    @FXML
    private Label lblPago;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtNum;

    @FXML
    private void initialize() {
        lblPago.setText("");
        btnPago.setOnAction(e -> realizarPago());
    }

    private void realizarPago() {
        String numero = txtNum.getText();
        String monto = txtMonto.getText();
        if (!validarNumero(numero)) {
            lblPago.setText("Número de Nequi inválido. Debe tener 10 dígitos.");
            return;
        }
        NequiAdapter nequiAdapter = new NequiAdapter(numero);
        String resultado = nequiAdapter.realizarPago(Double.parseDouble(monto));
        lblPago.setText(resultado);
    }

    private boolean validarNumero(String numero) {
        return numero.matches("\\d{10}");
    }

}
