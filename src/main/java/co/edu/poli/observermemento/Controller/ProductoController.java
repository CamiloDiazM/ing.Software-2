package co.edu.poli.observermemento.Controller;

import co.edu.poli.observermemento.model.GestorProductos;
import co.edu.poli.observermemento.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductoController {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> columnaNombre;
    @FXML
    private TableColumn<Producto, String> columnaDescripcion;
    @FXML
    private TableColumn<Producto, Double> columnaPrecio;
    @FXML
    private TextArea areaHistorial;

    private GestorProductos gestorProductos;
    private ObservableList<Producto> productos;

    @FXML
    public void initialize() {
        gestorProductos = new GestorProductos();

        // Crear y registrar productos
        Producto producto1 = new Producto("Laptop", "Laptop de alta gama", 1500.0);
        Producto producto2 = new Producto("Smartphone", "Teléfono inteligente", 800.0);
        Producto producto3 = new Producto("Tablet", "Tablet para uso diario", 400.0);

        gestorProductos.registrarProducto(producto1);
        gestorProductos.registrarProducto(producto2);
        gestorProductos.registrarProducto(producto3);

        // Cargar productos en la tabla
        productos = FXCollections.observableArrayList(gestorProductos.getProductos());
        columnaNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        columnaDescripcion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        columnaPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrecio()));

        tablaProductos.setItems(productos);
    }

    @FXML
    public void cambiarPrecio() {
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            TextInputDialog dialogo = new TextInputDialog();
            dialogo.setTitle("Cambiar Precio");
            dialogo.setHeaderText("Cambiar precio de " + productoSeleccionado.getNombre());
            dialogo.setContentText("Nuevo precio:");

            dialogo.showAndWait().ifPresent(precio -> {
                try {
                    double nuevoPrecio = Double.parseDouble(precio);
                    gestorProductos.cambiarPrecioProducto(productoSeleccionado.getNombre(), nuevoPrecio);
                    tablaProductos.refresh();
                } catch (NumberFormatException e) {
                    mostrarAlerta("Error", "El precio ingresado no es válido.");
                }
            });
        } else {
            mostrarAlerta("Advertencia", "Seleccione un producto.");
        }
    }

    @FXML
    public void mostrarHistorial() {
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            areaHistorial.clear();
            gestorProductos.mostrarHistorialProducto(productoSeleccionado.getNombre());
        } else {
            mostrarAlerta("Advertencia", "Seleccione un producto.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
