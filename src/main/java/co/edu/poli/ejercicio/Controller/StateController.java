package co.edu.poli.ejercicio.Controller;

import java.util.List;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Mediator.MediadorConcreto;
import co.edu.poli.ejercicio.Model.Mediator.Mediator;
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

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtIdCliente;

    @FXML
    private ChoiceBox<String> clientesBox;

    private Mediator mediador = new MediadorConcreto();

    @FXML
    public void initialize() {
        // Agregar productos predefinidos al mediador
        mediador.agregarProducto(new Producto("Laptop", 1200000));
        mediador.agregarProducto(new Producto("Mouse", 25000));
        mediador.agregarProducto(new Producto("Teclado", 50000));
        mediador.agregarProducto(new Producto("Monitor", 300000));

        // Inicializar ChoiceBox con productos del mediador
        productosBox.getItems().addAll(
                mediador.getProductos().stream().map(Producto::getNombre).toList());
        productosBox.setValue("Seleccione un producto");

        // Inicializar pedido y estado
        pedido = mediador.crearPedido(new Cliente("Dana", "5"));
        estadoPedido = new EmpezandoPedido();
        mssState.setText(estadoPedido.getNombreEstado());

        // Configurar acciones y propiedades de los elementos de la interfaz
        agregarAcciones();
        mssCarrito.setEditable(false);
        mssState.setEditable(false);
    }
    private EstadoPedido estadoPedido;
    private Pedido pedido;

    private void agregarAcciones() {
        btnAgregar.setOnAction(e -> onAgregarClick());
        btnCancelar.setOnAction(e -> CancelarClick());
        btnPagar.setOnAction(e -> onPagarClick());
        btnNuevoPedido.setOnAction(e -> onNuevoPedidoClick());
    }
    @FXML
    private void onCrearProductoClick() {
        String nombreProducto = txtNombreProducto.getText();
        String precioProductoStr = txtPrecioProducto.getText();

        if (nombreProducto.isEmpty() || precioProductoStr.isEmpty()) {
            mssState.setText("Por favor, complete todos los campos para crear un producto.");
            return;
        }

        try {
            double precioProducto = Double.parseDouble(precioProductoStr);
            Producto nuevoProducto = new Producto(nombreProducto, precioProducto);
            mediador.agregarProducto(nuevoProducto);

            // Actualizar ChoiceBox de productos
            productosBox.getItems().add(nuevoProducto.getNombre());
            mssState.setText("Producto creado exitosamente.");
            txtNombreProducto.clear();
            txtPrecioProducto.clear();
        } catch (NumberFormatException e) {
            mssState.setText("El precio debe ser un número válido.");
        }
    }

    @FXML
    private void onCrearClienteClick() {
        String nombreCliente = txtNombreCliente.getText();
        String idCliente = txtIdCliente.getText();

        if (nombreCliente.isEmpty() || idCliente.isEmpty()) {
            mssState.setText("Por favor, complete todos los campos para crear un cliente.");
            return;
        }

        Cliente nuevoCliente = new Cliente(nombreCliente, idCliente);
        mediador.getClientes().add(nuevoCliente);

        // Actualizar ChoiceBox de clientes
        clientesBox.getItems().add(nuevoCliente.getNombre());
        mssState.setText("Cliente creado exitosamente.");
        txtNombreCliente.clear();
        txtIdCliente.clear();
    }

    @FXML
    private void onEliminarProductoClick() {
        String productoSeleccionado = productosBox.getValue();

        if (productoSeleccionado == null || productoSeleccionado.equals("Seleccione un producto")) {
            mssState.setText("Seleccione un producto válido para eliminar.");
            return;
        }

        Producto productoAEliminar = pedido.getProductos().stream()
                .filter(p -> p.getNombre().equals(productoSeleccionado))
                .findFirst()
                .orElse(null);

        if (productoAEliminar != null) {
            pedido.getProductos().remove(productoAEliminar);
            actualizarCarrito();
            mssState.setText("Producto eliminado del carrito.");
        } else {
            mssState.setText("El producto no está en el carrito.");
        }
    }

    @FXML
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


    @FXML
    private void onAgregarClick() {
        String productoSeleccionado = productosBox.getValue();
        if (productoSeleccionado == null || productoSeleccionado.equals("Seleccione un producto")) {
            mssState.setText("Seleccione un producto válido.");
            return;
        }

        Producto producto = mediador.getProductos().stream()
                .filter(p -> p.getNombre().equals(productoSeleccionado))
                .findFirst()
                .orElse(null);

        if (producto != null) {
            if (estadoPedido.getNombreEstado().equals("Pidiendo") || estadoPedido.getNombreEstado().equals("Empezando Pedido")) {
                estadoPedido.agregarProducto(pedido, producto);
                estadoPedido = new Pidiendo();
                pedido.setEstado(estadoPedido);
                mssState.setText(estadoPedido.getNombreEstado());
                actualizarCarrito();
            } else {
                mssState.setText("No se puede agregar productos en este estado: " + estadoPedido.getNombreEstado());
            }
        }
    }

    @FXML
    private void onPagarClick() {
        if (estadoPedido.getNombreEstado().equals("Pidiendo")) {
            estadoPedido = new Pagado();
            estadoPedido.pagar(pedido);
            mssState.setText(estadoPedido.getNombreEstado());
            txtTotal.setText("Total: " + pedido.aceptar(new VisitorImpl()) + " Pagado!");
        } else {
            mssState.setText("No se puede pagar el pedido en este estado: " + estadoPedido.getNombreEstado());
        }
    }

    @FXML
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

        VisitorImpl visitor = new VisitorImpl();
        for (Producto producto : pedido.getProductos()) {
            mssCarrito.appendText(producto.aceptar(visitor) + "\n");
        }

        txtTotal.setText("Total: " + pedido.aceptar(visitor));
    }
}