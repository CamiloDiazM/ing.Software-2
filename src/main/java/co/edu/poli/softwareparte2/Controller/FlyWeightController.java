package co.edu.poli.softwareparte2.Controller;

import co.edu.poli.softwareparte2.Model.Producto;
import co.edu.poli.softwareparte2.Model.ProveedorFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class FlyWeightController {

    @FXML
    private ListView<String> listView;

    @FXML
    public void initialize() {
        // Crear productos con proveedores compartidos
        var proveedorA = ProveedorFactory.getProveedor("Proveedor A");
        var proveedorB = ProveedorFactory.getProveedor("Proveedor B");

        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Producto 1", proveedorA));
        productos.add(new Producto("Producto 2", proveedorB));
        productos.add(new Producto("Producto 3", proveedorA));
        productos.add(new Producto("Producto 4", proveedorB));

        // Agregar productos al ListView
        for (Producto producto : productos) {
            listView.getItems().add(producto.toString());
        }
    }
}
