package co.edu.poli.observermemento.Controller;

import co.edu.poli.observermemento.model.Cliente;
import co.edu.poli.observermemento.model.GestorProductos;
import co.edu.poli.observermemento.model.ObservadorProducto;
import co.edu.poli.observermemento.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

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

        // Crear y registrar clientes
        Cliente cliente1 = new Cliente("Juan Pérez", "juan.perez@example.com");
        Cliente cliente2 = new Cliente("María López", "maria.lopez@example.com");
        Cliente cliente3 = new Cliente("Carlos Gómez", "carlos.gomez@example.com");

        // Suscribir clientes a productos
        producto1.agregarObservador(cliente1);
        producto1.agregarObservador(cliente2);
        producto2.agregarObservador(cliente2);
        producto3.agregarObservador(cliente3);

        // Cargar productos en la tabla
        productos = FXCollections.observableArrayList(gestorProductos.getProductos());
        columnaNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        columnaDescripcion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        columnaPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrecio()));

        tablaProductos.setItems(productos);
    }
    @FXML
    public void mostrarNotificaciones() {
        StringBuilder notificaciones = new StringBuilder("Notificaciones de Clientes:\n");
        for (Producto producto : gestorProductos.getProductos()) {
            for (ObservadorProducto observador : producto.getObservadores()) {
                if (observador instanceof Cliente) {
                    Cliente cliente = (Cliente) observador;
                    notificaciones.append("Cliente: ").append(cliente.getNombre())
                            .append(" - Producto: ").append(producto.getNombre())
                            .append("\n");
                }
            }
        }
        // Actualiza el contenido del TextArea en lugar de imprimir en consola
        areaHistorial.setText(notificaciones.toString());
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
                    mostrarNotificaciones();
                } catch (NumberFormatException e) {
                    mostrarAlerta("Error", "El precio ingresado no es válido.");
                }
            });
        } else {
            mostrarAlerta("Advertencia", "Seleccione un producto.");
        }
    }
    @FXML
    public void restaurarPrecio() {
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            List<Producto.ProductoMemento> historialProducto = gestorProductos.getHistorialPrecios()
                    .getHistorialProducto(productoSeleccionado.getNombre());
            if (historialProducto != null && !historialProducto.isEmpty()) {
                int indiceUltimo = historialProducto.size() - 1; // Último índice del historial
                gestorProductos.restaurarPrecioProducto(productoSeleccionado.getNombre(), indiceUltimo);
                tablaProductos.refresh();
                mostrarNotificaciones();
            } else {
                mostrarAlerta("Advertencia", "No hay historial para restaurar.");
            }
        } else {
            mostrarAlerta("Advertencia", "Seleccione un producto.");
        }
    }

    @FXML
    public void mostrarHistorial() {
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            StringBuilder historial = new StringBuilder("Historial de precios para: ")
                    .append(productoSeleccionado.getNombre()).append("\n");
            List<Producto.ProductoMemento> historialProducto = gestorProductos.getHistorialPrecios()
                    .getHistorialProducto(productoSeleccionado.getNombre());
            for (int i = 0; i < historialProducto.size(); i++) {
                Producto.ProductoMemento memento = historialProducto.get(i);
                historial.append("[").append(i).append("] ").append(memento).append("\n");
            }
            areaHistorial.setText(historial.toString());
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
