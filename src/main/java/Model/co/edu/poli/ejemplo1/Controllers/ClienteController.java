package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.ClienteDAOimp;
import Model.co.edu.poli.ejemplo1.Model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteController {

    private ClienteDAOimp clienteDAO;

    @FXML
    private Button btnSalir;

    public ClienteController() throws SQLException {
        this.clienteDAO = new ClienteDAOimp();
    }

    @FXML
    public void registrarCliente() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Registrar Cliente");
        dialog.setHeaderText("Ingrese los detalles del cliente");

        dialog.setContentText("ID del cliente:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String id = result.get();

        dialog.setContentText("Nombre del cliente:");
        result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String nombre = result.get();

        Cliente cliente = new Cliente(id, nombre);
        clienteDAO.registrar(cliente);
        showAlert("Cliente registrado exitosamente.");
    }

    @FXML
    public void mostrarTodosLosClientes() {
        List<Cliente> clientes = clienteDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : clientes) {
            sb.append(cliente).append("\n");
        }
        showAlert(sb.toString());
    }

    @FXML
    public void actualizarCliente() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Actualizar Cliente");
        dialog.setHeaderText("Ingrese el ID del cliente a actualizar");

        dialog.setContentText("ID del cliente:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String id = result.get();

        Cliente cliente = clienteDAO.obtenerPorId(id);
        if (cliente != null) {
            dialog.setContentText("Nuevo nombre del cliente:");
            result = dialog.showAndWait();
            if (!result.isPresent()) return;
            String nombre = result.get();
            cliente.setNombre(nombre);

            clienteDAO.actualizar(cliente);
            showAlert("Cliente actualizado exitosamente.");
        } else {
            showAlert("Cliente no encontrado.");
        }
    }

    @FXML
    public void eliminarCliente() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Cliente");
        dialog.setHeaderText("Ingrese el ID del cliente a eliminar");

        dialog.setContentText("ID del cliente:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) return;
        String id = result.get();

        clienteDAO.eliminar(id);
        showAlert("Cliente eliminado exitosamente.");
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}