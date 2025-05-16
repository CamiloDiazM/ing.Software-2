package co.edu.poli.ejercicio.Controller;

import java.util.List;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;
import co.edu.poli.ejercicio.Model.State.Cancelado;
import co.edu.poli.ejercicio.Model.State.EmpezandoPedido;
import co.edu.poli.ejercicio.Model.State.EstadoPedido;
import co.edu.poli.ejercicio.Model.State.Pagado;
import co.edu.poli.ejercicio.Model.State.Pidiendo;
import co.edu.poli.ejercicio.Model.Visitor.VisitorImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StateController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnNuevoPedido;

    @FXML
    private Button btnPagar;

    @FXML
    private TextArea mssCarrito;

    @FXML
    private TextArea mssState;

    @FXML
    private ChoiceBox<String> productosBox;

    @FXML
    private Label txtTotal;

    private List<Producto> productos = List.of(
            new Producto("Laptop", 1200000),
            new Producto("Mouse", 25000),
            new Producto("Teclado", 50000),
            new Producto("Monitor", 300000));

    private EstadoPedido estadoPedido;
    private Pedido pedido;

    @FXML
    public void initialize() {
        pedido = new Pedido(new Cliente("Dana", "5"));

        estadoPedido = new EmpezandoPedido();
        mssState.setText(estadoPedido.getNombreEstado());
        productosBox.getItems().addAll(
                productos.stream().map(Producto::getNombre).toList());
        productosBox.setValue("Seleccione un producto");
        agregarAcciones();
        mssCarrito.setEditable(false);
        mssState.setEditable(false);
    }

    private void agregarAcciones() {
        btnAgregar.setOnAction(e -> onAgregarClick());
        btnCancelar.setOnAction(e -> CancelarClick());
        btnPagar.setOnAction(e -> onPagarClick());
        btnNuevoPedido.setOnAction(e -> onNuevoPedidoClick());
    }

    private void CancelarClick() {
        if (estadoPedido.getNombreEstado().equals("Pidiendo") ||
                estadoPedido.getNombreEstado().equals("Empezando Pedido")) {
            estadoPedido = new Cancelado();
            pedido.setEstado(estadoPedido);
            mssState.setText(estadoPedido.getNombreEstado());
        } else {
            mssState.setText("No se puede cancelar el pedido en este estado: " + estadoPedido.getNombreEstado());
        }
    }

    private Object onAgregarClick() {
        String productoSeleccionado = productosBox.getValue();
        double precio = productos.stream()
                .filter(p -> p.getNombre().equals(productoSeleccionado))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"))
                .getPrecio();

        if (estadoPedido.getNombreEstado().equals("Pidiendo") ||
                estadoPedido.getNombreEstado().equals("Empezando Pedido")) {
            estadoPedido.agregarProducto(pedido, new Producto(productoSeleccionado, precio));
            estadoPedido = new Pidiendo();
            pedido.setEstado(estadoPedido);
            mssState.setText(estadoPedido.getNombreEstado());
            actualizarCarrito();
        } else {
            mssState.setText("No se puede agregar productos en este estado: " + estadoPedido.getNombreEstado());
        }

        return null;

    }

    private Object onPagarClick() {
        if (estadoPedido.getNombreEstado().equals("Pidiendo")) {
            estadoPedido = new Pagado();
            estadoPedido.pagar(pedido);
            mssState.setText(estadoPedido.getNombreEstado());
            txtTotal.setText("Total :" + pedido.aceptar(new VisitorImpl()) + " Pagado!");
        } else {
            mssState.setText("No se puede pagar el pedido en este estado: " + estadoPedido.getNombreEstado());
        }

        return null;
    }

    private void onNuevoPedidoClick() {
        estadoPedido = new EmpezandoPedido();
        pedido.setEstado(estadoPedido);
        mssState.setText(estadoPedido.getNombreEstado());
        pedido.getProductos().clear();
        mssCarrito.clear();
        productosBox.setValue("Seleccione un producto");
    }

    private void actualizarCarrito() {
        mssCarrito.clear();
        mssCarrito.appendText("Productos en el carrito:\n");

        for (Producto producto : pedido.getProductos()) {
            mssCarrito.appendText(producto.getNombre() + "\n");
        }
        txtTotal.setText("Total :" + pedido.aceptar(new VisitorImpl()));
    }
}
