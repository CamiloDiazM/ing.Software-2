package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Model.Bridge.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class EnvioController {

    @FXML
    private ComboBox<String> envioComboBox;

    @FXML
    private ComboBox<String> mercanciaComboBox;

    @FXML
    private Label label;

    private TipoMercancia tipoMercancia;
    private String tipoEnvio;

    @FXML
    private void initialize() {
        envioComboBox.getItems().addAll("Envio Nacional", "Envio Internacional");
        mercanciaComboBox.getItems().addAll("Documentos", "Carga Normal", "Carga Fragil");
    }

    @FXML
    private void handleEnvioSelection() {
        tipoEnvio = envioComboBox.getValue();
    }

    @FXML
    private void handleMercanciaSelection() {
        String selectedMercancia = mercanciaComboBox.getValue();
        switch (selectedMercancia) {
            case "Documentos":
                TextInputDialog docDialog = new TextInputDialog();
                docDialog.setHeaderText("Ingrese los detalles del documento");
                docDialog.setContentText(" Descripción: ");
                Optional<String> descripcionDoc = docDialog.showAndWait();
                docDialog.setContentText(" Número de Páginas: ");
                Optional<String> numeroPaginas = docDialog.showAndWait();
                tipoMercancia = new Documentos(descripcionDoc.orElse(""), numeroPaginas.orElse(""));
                break;
            case "Carga Normal":
                TextInputDialog cargaNormalDialog = new TextInputDialog();
                cargaNormalDialog.setHeaderText("Ingrese los detalles de la carga normal");
                cargaNormalDialog.setContentText(" Peso: ");
                Optional<String> peso = cargaNormalDialog.showAndWait();
                cargaNormalDialog.setContentText(" Dimensiones: ");
                Optional<String> dimensiones = cargaNormalDialog.showAndWait();
                cargaNormalDialog.setContentText(" Contenido: ");
                Optional<String> contenido = cargaNormalDialog.showAndWait();
                tipoMercancia = new CargaNormal(Double.parseDouble(peso.orElse("0")), Double.parseDouble(dimensiones.orElse("0")), contenido.orElse(""));
                break;
            case "Carga Fragil":
                TextInputDialog cargaFragilDialog = new TextInputDialog();
                cargaFragilDialog.setHeaderText("Ingrese los detalles de la carga frágil");
                cargaFragilDialog.setContentText(" Peso: ");
                Optional<String> pesoFragil = cargaFragilDialog.showAndWait();
                cargaFragilDialog.setContentText(" Dimensiones: ");
                Optional<String> dimensionesFragil = cargaFragilDialog.showAndWait();
                cargaFragilDialog.setContentText(" Contenido: ");
                Optional<String> contenidoFragil = cargaFragilDialog.showAndWait();
                cargaFragilDialog.setContentText(" Material: ");
                Optional<String> material = cargaFragilDialog.showAndWait();
                tipoMercancia = new CargaFragil(Double.parseDouble(pesoFragil.orElse("0")), dimensionesFragil.orElse(""), contenidoFragil.orElse(""), material.orElse(""));
                break;
        }
        label.setText("Tipo de mercancía seleccionado: " + selectedMercancia);
    }

    @FXML
    private void handleRealizarEnvio() {
        if (tipoMercancia != null && tipoEnvio != null) {
            String resultadoEnvio;
            if (tipoEnvio.equals("Envio Nacional ")) {
                EnvioNacional envioNacional = new EnvioNacional(tipoMercancia, 1, " Juan Perez ", "2023-10-01", "2023-10-05", "Bogotá", "Calle 123", "Cundinamarca");
                resultadoEnvio = envioNacional.realizarEnvio();
            } else {
                EnvioInternacional envioInternacional = new EnvioInternacional(tipoMercancia, 2, " Maria Gomez ", "2023-10-01", "2023-10-10", "Madrid", "Calle 456", "España", 100.0, "DHL");
                resultadoEnvio = envioInternacional.realizarEnvio();
            }
            label.setText(resultadoEnvio);
        } else {
            label.setText("Seleccione un tipo de envío y mercancía primero.");
        }
    }
}