package co.edu.poli.ejercicio.Controller;

import co.edu.poli.ejercicio.Model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {

    @FXML
    private TextField nombreField, precioField, cantidadField, stockField;
    @FXML
    private TableView<Producto> productosTable;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, Double> precioColumn;
    @FXML
    private TableColumn<Producto, Integer> cantidadColumn, stockColumn;

    private GestorPedido gestorPedido;
    private InvocadorComando invocador;
    private ObservableList<Producto> productos;

    public MenuController() {
        Pedido pedido = new Pedido(new Cliente("Juan Pérez", "juan@example.com", "123"));
        gestorPedido = new GestorPedido(pedido);
        invocador = new InvocadorComando();
        productos = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        nombreColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        precioColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecio()).asObject());
        cantidadColumn.setCellValueFactory(data -> new SimpleIntegerProperty(0).asObject()); // Si no hay cantidad en Producto
        stockColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getStock()).asObject());
        productosTable.setItems(productos);
    }

    @FXML
    private void agregarProductoDesdeFormulario() {
        try {
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Producto producto = new Producto(nombre, precio, stock, "ID-" + nombre);
            invocador.setComando(new AgregarProductosComando(gestorPedido, producto, cantidad));
            invocador.ejecutarComando();

            productos.add(producto);
            mostrarAlerta("Producto agregado", "El producto se agregó correctamente.");
            limpiarFormulario();
        } catch (Exception ex) {
            mostrarAlerta("Error", "Datos inválidos. Por favor, intente de nuevo.");
        }
    }

    private void limpiarFormulario() {
        nombreField.clear();
        precioField.clear();
        cantidadField.clear();
        stockField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void mostrarAgregarProducto() {
        mostrarAlerta("Agregar Producto", "Funcionalidad para agregar un producto.");
    }

    @FXML
    private void ejecutarReducirStock() {
        invocador.setComando(new ReducirStockComando(gestorPedido));
        invocador.ejecutarComando();
        mostrarAlerta("Reducir Stock", "El stock ha sido reducido.");
    }

    @FXML
    private void mostrarCalcularTotal() {
        double total = gestorPedido.calcularTotal();
        mostrarAlerta("Calcular Total", "El total del pedido es: $" + total);
    }

    @FXML
    private void ejecutarProcesarPedido() {
        gestorPedido.procesarPedido();
        mostrarAlerta("Procesar Pedido", "El pedido ha sido procesado.");
    }

    @FXML
    private void mostrarAcercaDe() {
        mostrarAlerta("Acerca de", "Aplicación de Gestión de Pedidos v1.0");
    }
}