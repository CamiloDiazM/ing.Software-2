package co.edu.poli.ejercicio.Controller;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Mediator.MediadorConcreto;
import co.edu.poli.ejercicio.Model.Mediator.Mediator;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;
import co.edu.poli.ejercicio.Model.Visitor.VisitorImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class StateController {

    @FXML private Button btnAgregar;
    @FXML private Button btnCancelar;
    @FXML private Button btnNuevoPedido;
    @FXML private Button btnPagar;
    @FXML private Button btnCrearCliente;
    @FXML private Button btnCrearProductoCatalogo;
    @FXML private Button btnEliminarProductoCatalogo;
    @FXML private Button btnEliminarDelPedido;

    @FXML private TextArea mssCarrito;
    @FXML private TextArea mssState;
    @FXML private ChoiceBox<Producto> productosBox;
    @FXML private Label txtTotal;
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtIdCliente;
    @FXML private ChoiceBox<Cliente> clientesBox;

    @FXML private TextField txtNuevoNombreProducto;
    @FXML private TextField txtNuevoPrecioProducto;

    private Mediator mediador;
    private VisitorImpl visitor;

    @FXML
    public void initialize() {
        mediador = new MediadorConcreto();
        visitor = new VisitorImpl();

        productosBox.setConverter(new StringConverter<Producto>() {
            @Override public String toString(Producto producto) { return producto == null ? null : producto.getNombre() + " ($" + producto.getPrecio() + ")"; }
            @Override public Producto fromString(String string) { return null; }
        });

        clientesBox.setConverter(new StringConverter<Cliente>() {
            @Override public String toString(Cliente cliente) { return cliente == null ? null : cliente.getNombre() + " (ID: " + cliente.getId() + ")"; }
            @Override public Cliente fromString(String string) { return null; }
        });

        actualizarProductosCatalogoChoiceBox();
        actualizarClientesChoiceBox();
        productosBox.setValue(null);
        clientesBox.setValue(null);

        mssState.setText("Sistema listo. Seleccione un cliente e inicie un pedido.");
        actualizarVistaPedido();
        mssCarrito.setEditable(false);
        mssState.setEditable(false);
    }

    @FXML
    private void onNuevoPedidoClick() {
        Cliente clienteSeleccionado = clientesBox.getValue();
        if (clienteSeleccionado == null) {
            mssState.setText("Por favor, seleccione un cliente para el nuevo pedido.");
            return;
        }
        Pedido nuevoPedido = mediador.iniciarNuevoPedido(clienteSeleccionado.getId());
        if (nuevoPedido != null) {
            mssState.setText("Nuevo pedido iniciado para " + clienteSeleccionado.getNombre() + ". Estado: " + nuevoPedido.getEstado().getNombreEstado());
        } else {
            mssState.setText("No se pudo iniciar el pedido. Verifique el cliente.");
        }
        actualizarVistaPedido();
    }

    @FXML
    private void onAgregarClick() {
        Producto productoSeleccionado = productosBox.getValue();
        if (productoSeleccionado == null) {
            mssState.setText("Seleccione un producto válido del catálogo.");
            return;
        }
        String mensajeDelEstado = mediador.agregarProductoAPedidoActual(productoSeleccionado.getNombre());
        mssState.setText(mensajeDelEstado);
        actualizarVistaPedido();
    }

    @FXML
    private void onEliminarDelPedidoClick() {
        Producto productoSeleccionado = productosBox.getValue();
        if (productoSeleccionado == null) {
            mssState.setText("Seleccione un producto del catálogo para intentar eliminarlo del pedido.");
            return;
        }
        String mensajeDelEstado = mediador.eliminarProductoDePedidoActual(productoSeleccionado.getNombre());
        mssState.setText(mensajeDelEstado);
        actualizarVistaPedido();
    }

    @FXML
    private void onCancelarPedidoClick() {
        String mensajeDelEstado = mediador.cancelarPedidoActual();
        mssState.setText(mensajeDelEstado);
        actualizarVistaPedido();
    }

    @FXML
    private void onPagarClick() {
        Pedido pedidoAPagar = mediador.obtenerPedidoActual();
        if (pedidoAPagar == null) {
            mssState.setText("No hay pedido activo para pagar.");
            actualizarVistaPedido();
            return;
        }

        String mensajeDelEstado = pedidoAPagar.getEstado().pagar(pedidoAPagar);
        mssState.setText(mensajeDelEstado);
        actualizarVistaPedido();
    }

    private void actualizarVistaPedido() {
        Pedido pedido = mediador.obtenerPedidoActual();
        mssCarrito.clear();
        txtTotal.setText("Total: $0.00");

        if (pedido != null) {
            mssCarrito.appendText("Cliente: " + pedido.getCliente().getNombre() + " (ID: " + pedido.getCliente().getId() + ")\n");
            mssCarrito.appendText("Estado Actual del Pedido: " + pedido.getEstado().getNombreEstado() + "\n");
            mssCarrito.appendText("---- Productos en el carrito ----\n");
            if (pedido.getProductos().isEmpty()) {
                mssCarrito.appendText("(Carrito vacío)\n");
            } else {
                for (Producto p : pedido.getProductos()) {
                    mssCarrito.appendText(p.aceptar(visitor) + "\n");
                }
            }
            String totalTexto = "Total: " + pedido.aceptar(visitor);
            if ("Pagado".equals(pedido.getEstado().getNombreEstado())) {
                totalTexto += " ¡Pagado!";
            }
            txtTotal.setText(totalTexto);
        } else {
            mssCarrito.setText("No hay pedido activo.");
        }
    }

    @FXML
    private void onCrearClienteClick() {
        String nombre = txtNombreCliente.getText();
        String id = txtIdCliente.getText();
        if (nombre.isEmpty() || id.isEmpty()) {
            mssState.setText("Campos incompletos para crear cliente.");
            return;
        }
        String mensaje = mediador.crearCliente(nombre, id);
        mssState.setText(mensaje);
        if (!mensaje.startsWith("Error:")) {
            actualizarClientesChoiceBox();
            txtNombreCliente.clear();
            txtIdCliente.clear();
        }
    }

    private void actualizarClientesChoiceBox() {
        Cliente clienteSeleccionado = clientesBox.getValue();
        clientesBox.getItems().clear();
        clientesBox.getItems().addAll(mediador.obtenerClientes());
        if (clienteSeleccionado != null && clientesBox.getItems().contains(clienteSeleccionado)) {
            clientesBox.setValue(clienteSeleccionado);
        } else {
            clientesBox.setValue(null);
        }
    }

    @FXML
    private void onCrearProductoCatalogoClick() {
        String nombre = txtNuevoNombreProducto.getText();
        String precioStr = txtNuevoPrecioProducto.getText();
        if (nombre.isEmpty() || precioStr.isEmpty()) {
            mssState.setText("Ingrese nombre y precio para el nuevo producto.");
            return;
        }
        try {
            double precio = Double.parseDouble(precioStr);
            String mensaje = mediador.crearProductoCatalogo(nombre, precio);
            mssState.setText(mensaje);
            if (!mensaje.startsWith("Error:")) {
                actualizarProductosCatalogoChoiceBox();
                txtNuevoNombreProducto.clear();
                txtNuevoPrecioProducto.clear();
            }
        } catch (NumberFormatException e) {
            mssState.setText("Error: El precio debe ser un número.");
        }
    }

    @FXML
    private void onEliminarProductoCatalogoClick() {
        Producto productoSeleccionado = productosBox.getValue();
        if (productoSeleccionado != null) {
            String mensaje = mediador.eliminarProductoCatalogo(productoSeleccionado.getNombre());
            mssState.setText(mensaje);
            actualizarProductosCatalogoChoiceBox();
            actualizarVistaPedido();
        } else {
            mssState.setText("Seleccione un producto del catálogo para eliminar.");
        }
    }

    private void actualizarProductosCatalogoChoiceBox() {
        Producto productoSeleccionadoPrevio = productosBox.getValue();
        productosBox.getItems().clear();
        productosBox.getItems().addAll(mediador.obtenerProductosCatalogo());

        if (productoSeleccionadoPrevio != null) {
            productosBox.getItems().stream()
                    .filter(p -> p.getNombre().equals(productoSeleccionadoPrevio.getNombre()))
                    .findFirst()
                    .ifPresent(productosBox::setValue);
        }
        if (productosBox.getValue() == null && !productosBox.getItems().isEmpty()) {
            // Opcional: no seleccionar nada o el primero.
        } else if (productosBox.getItems().isEmpty()){
            productosBox.setValue(null);
        }
    }
}