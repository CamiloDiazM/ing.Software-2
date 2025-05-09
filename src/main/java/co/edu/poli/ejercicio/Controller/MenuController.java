package co.edu.poli.ejercicio.Controller;

import co.edu.poli.ejercicio.Model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class MenuController {

    // Campos FXML para agregar producto al pedido
    @FXML private TextField nombreField;
    @FXML private TextField precioField;
    @FXML private TextField cantidadField; // Cantidad a agregar al pedido
    @FXML private TextField stockInicialField; // Stock inicial al definir un nuevo producto

    // TableView para mostrar los productos en el pedido actual (carrito)
    @FXML private TableView<ItemPedidoView> pedidoTable;
    @FXML private TableColumn<ItemPedidoView, String> nombreProductoPedidoColumn;
    @FXML private TableColumn<ItemPedidoView, Double> precioProductoPedidoColumn;
    @FXML private TableColumn<ItemPedidoView, Integer> cantidadProductoPedidoColumn;
    @FXML private TableColumn<ItemPedidoView, Integer> stockDisponibleProductoColumn;

    // Botón para eliminar producto seleccionado
    @FXML private Button eliminarProductoButton; // NUEVO BOTÓN

    // Selección de Estrategia de Cálculo
    @FXML private ComboBox<String> estrategiaCalculoComboBox;

    // Label para mostrar el total del pedido
    @FXML private Label totalPedidoLabel;

    // TextArea para logs y feedback de la Chain of Responsibility
    @FXML private TextArea logTextArea;

    private GestorPedido gestorPedido;
    private InvocadorComando invocador;
    private ObservableList<ItemPedidoView> itemsDelPedidoView;
    private ObservableList<Producto> productosDisponibles;


    public MenuController() {
        Cliente cliente = new Cliente("Juan Pérez (GUI v2)", "juan.gui.v2@example.com", "GUI-V2-123");
        Pedido pedidoActual = new Pedido(cliente);
        gestorPedido = new GestorPedido(pedidoActual);
        invocador = new InvocadorComando();

        productosDisponibles = FXCollections.observableArrayList();
        itemsDelPedidoView = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        gestorPedido.setEstrategia(new CalculoSinDescuento());
        configurarCadenaDeResponsabilidad();

        nombreProductoPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioProductoPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        cantidadProductoPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadEnPedido"));
        stockDisponibleProductoColumn.setCellValueFactory(new PropertyValueFactory<>("stockDisponible"));
        pedidoTable.setItems(itemsDelPedidoView);

        // Habilitar/deshabilitar botón de eliminar según selección
        eliminarProductoButton.disableProperty().bind(pedidoTable.getSelectionModel().selectedItemProperty().isNull());

        ObservableList<String> estrategias = FXCollections.observableArrayList("Sin Descuento", "Con Descuento (10%)");
        estrategiaCalculoComboBox.setItems(estrategias);
        estrategiaCalculoComboBox.setValue("Sin Descuento");
        estrategiaCalculoComboBox.setOnAction(event -> cambiarEstrategiaCalculo());

        cargarProductosEjemplo();
        actualizarLabelTotal();
        log("Controlador inicializado. Carrito listo.");
    }

    private void cargarProductosEjemplo() {
        productosDisponibles.add(new Producto("Laptop Gamer Pro", 3800000, 8, "PROD-LGPRO"));
        productosDisponibles.add(new Producto("Mouse Ergonómico", 85000, 40, "PROD-MERGO"));
        productosDisponibles.add(new Producto("Teclado Iluminado", 220000, 15, "PROD-TLED"));
        log(productosDisponibles.size() + " productos de ejemplo cargados.");
    }

    private void configurarCadenaDeResponsabilidad() {
        // Consumer para logs (opcional para pasarlo a los handlers)
        // Consumer<String> guiLogUpdater = mensaje -> logTextArea.appendText(mensaje + "\n");

        // Los handlers usarán System.out.println por ahora, se pueden modificar para usar el logger.
        Handler handlerStock = new StockHandler();
        Handler handlerDescuento = new DescuentoHandler();
        Handler handlerFactura = new FacturaHandler();

        handlerStock.setNext(handlerDescuento);
        handlerDescuento.setNext(handlerFactura);
        gestorPedido.setHandlerInicial(handlerStock);
        log("Cadena de responsabilidad configurada: Stock -> Descuento -> Factura.");
    }

    private void log(String message) {
        if (logTextArea != null) {
            logTextArea.appendText(message + "\n");
        }
        System.out.println(message);
    }

    @FXML
    private void agregarProductoAlPedidoAction() {
        try {
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());
            int stock = Integer.parseInt(stockInicialField.getText());

            if (nombre.isEmpty() || precio <= 0 || cantidad <= 0 || stock < 0) {
                mostrarAlerta("Error de Validación", "Datos inválidos para producto/cantidad.");
                return;
            }

            Producto productoAAgregar = productosDisponibles.stream()
                    .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                    .findFirst()
                    .orElseGet(() -> {
                        Producto nuevoProd = new Producto(nombre, precio, stock, "PROD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
                        productosDisponibles.add(nuevoProd);
                        log("Nuevo producto '" + nombre + "' creado y añadido a disponibles (Stock: " + stock + ").");
                        return nuevoProd;
                    });

            // Si el producto existía, actualizamos su precio y stock definido en el formulario (opcional)
            if (productosDisponibles.contains(productoAAgregar) && (productoAAgregar.getPrecio() != precio || productoAAgregar.getStock() != stock) ){
                productoAAgregar.setPrecio(precio);
                productoAAgregar.setStock(stock); // El stock del catálogo se actualiza
                log("Producto existente '" + nombre + "' actualizado. Nuevo Precio: " + precio + ", Nuevo Stock Catálogo: " + stock);
            }


            if (cantidad > productoAAgregar.getStock()) {
                mostrarAlerta("Stock Insuficiente", "No hay suficiente stock para '" + productoAAgregar.getNombre() + "'. Disponible: " + productoAAgregar.getStock() + ", Solicitado: " + cantidad);
                return;
            }

            Comando agregarCmd = new AgregarProductosComando(gestorPedido, productoAAgregar, cantidad);
            invocador.setComando(agregarCmd);
            invocador.ejecutarComando();

            log("Comando 'AgregarProducto' ejecutado para: " + productoAAgregar.getNombre() + " x" + cantidad);
            actualizarVistaPedido();
            actualizarLabelTotal();
            limpiarFormularioProducto();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Por favor, ingrese números válidos para precio, cantidad y stock.");
        } catch (Exception e) {
            mostrarAlerta("Error Inesperado al Agregar", "Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --- NUEVO MÉTODO DE ACCIÓN ---
    @FXML
    private void eliminarProductoSeleccionadoAction() {
        ItemPedidoView itemSeleccionado = pedidoTable.getSelectionModel().getSelectedItem();
        if (itemSeleccionado == null) {
            mostrarAlerta("Error", "Por favor, seleccione un producto de la tabla para eliminar.");
            return;
        }

        Producto productoAEliminar = itemSeleccionado.getProductoOriginal();

        // Confirmación (opcional pero recomendado)
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("Eliminar Producto del Pedido");
        confirmacion.setContentText("¿Está seguro de que desea eliminar '" + productoAEliminar.getNombre() + "' del pedido?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Comando eliminarCmd = new EliminarProductoComando(gestorPedido, productoAEliminar);
            invocador.setComando(eliminarCmd);
            invocador.ejecutarComando();

            log("Comando 'EliminarProducto' ejecutado para: " + productoAEliminar.getNombre());
            actualizarVistaPedido();
            actualizarLabelTotal();
        } else {
            log("Eliminación de '" + productoAEliminar.getNombre() + "' cancelada por el usuario.");
        }
    }


    @FXML
    private void reducirStockDelPedidoAction() {
        if (gestorPedido.getPedido().getProductos().isEmpty()) {
            mostrarAlerta("Pedido Vacío", "No hay productos en el pedido para reducir stock.");
            return;
        }
        Comando reducirStockCmd = new ReducirStockComando(gestorPedido);
        invocador.setComando(reducirStockCmd);
        invocador.ejecutarComando();

        log("Comando 'ReducirStock' ejecutado para los productos del pedido.");
        actualizarVistaPedido(); // Refrescar stock en la tabla
    }

    @FXML
    private void calcularTotalPedidoAction() {
        actualizarLabelTotal();
        log("Cálculo de total solicitado. Total actual: " + totalPedidoLabel.getText());
    }

    @FXML
    private void procesarPedidoAction() {
        if (gestorPedido.getPedido().getProductos().isEmpty()) {
            mostrarAlerta("Pedido Vacío", "No se puede procesar un pedido vacío.");
            log("Intento de procesar pedido vacío.");
            return;
        }

        log("\n--- INICIANDO PROCESAMIENTO DE PEDIDO (GUI) ---");
        // Aquí podrías añadir una confirmación antes de procesar.
        // La lógica de `gestorPedido.procesarPedido()` ya incluye una verificación de stock global
        // si se mantuvo esa lógica en `GestorPedido`.
        gestorPedido.procesarPedido(); // Esto activa la cadena de responsabilidad

        // Verificar si el pedido aún contiene productos después del intento de procesamiento
        // Esto es una heurística simple. Un mejor enfoque sería que `procesarPedido` devuelva un estado.
        if (!gestorPedido.getPedido().getProductos().isEmpty()) {
            // Si la cadena de procesamiento fue "exitosa" (o al menos no vació el pedido por un error fatal previo),
            // y la política es limpiar el carrito después:
            log("Procesamiento del pedido finalizado (o al menos la cadena se ejecutó). Procediendo a limpiar el carrito...");

            Comando limpiarCmd = new LimpiarPedidoComando(gestorPedido);
            invocador.setComando(limpiarCmd);
            invocador.ejecutarComando();

            log("Comando 'LimpiarPedido' ejecutado.");
        } else {
            // Si el pedido se vació ANTES de llamar a limpiar (ej, por un error en `procesarPedido` que lo vació),
            // o si `procesarPedido` mismo lo limpió (lo cual no hace en la implementación actual).
            log("El pedido ya estaba vacío después de intentar procesarlo o el procesamiento falló críticamente.");
        }

        log("--- FIN PROCESAMIENTO DE PEDIDO (GUI) ---");
        actualizarVistaPedido(); // Debería mostrar la tabla vacía
        actualizarLabelTotal();  // Debería ser $0.00
    }


    private void cambiarEstrategiaCalculo() {
        String seleccion = estrategiaCalculoComboBox.getValue();
        if ("Con Descuento (10%)".equals(seleccion)) {
            gestorPedido.setEstrategia(new CalculoConDescuento());
            log("Estrategia de cálculo cambiada a: Con Descuento (10%)");
        } else {
            gestorPedido.setEstrategia(new CalculoSinDescuento());
            log("Estrategia de cálculo cambiada a: Sin Descuento");
        }
        actualizarLabelTotal();
    }

    private void actualizarVistaPedido() {
        itemsDelPedidoView.clear();
        if (gestorPedido.getPedido() != null && gestorPedido.getPedido().getProductos() != null) {
            for (Map.Entry<Producto, Integer> entry : gestorPedido.getPedido().getProductos().entrySet()) {
                Producto p = entry.getKey();
                // Asegurarse de que p no es nulo antes de intentar acceder a sus propiedades
                if (p != null) {
                    int cantidadEnPedido = entry.getValue();
                    // Para el stock disponible, obtenemos el del producto en nuestro catálogo (productosDisponibles)
                    Producto productoDeCatalogo = productosDisponibles.stream()
                            .filter(pdb -> pdb.getIdProducto().equals(p.getIdProducto()))
                            .findFirst().orElse(p); // Fallback al producto del pedido si no se encuentra (no debería ocurrir)

                    itemsDelPedidoView.add(new ItemPedidoView(productoDeCatalogo, cantidadEnPedido));
                }
            }
        }
        pedidoTable.refresh();
        log("Vista del pedido actualizada. Items en carrito: " + itemsDelPedidoView.size());
    }

    private void actualizarLabelTotal() {
        double total = gestorPedido.calcularTotal();
        if (totalPedidoLabel != null) {
            totalPedidoLabel.setText(String.format("Total: $%.2f", total));
        }
    }

    private void limpiarFormularioProducto() {
        nombreField.clear();
        precioField.clear();
        cantidadField.clear();
        stockInicialField.clear();
        nombreField.requestFocus();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (titulo.toLowerCase().contains("error")) {
            alert.setAlertType(Alert.AlertType.ERROR);
        }
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        log("Alerta: " + titulo + " - " + mensaje);
    }

    @FXML
    private void mostrarAcercaDe() {
        mostrarAlerta("Acerca de", "Gestión de Pedidos v1.2\nPatrones: Command, Strategy, Chain of Responsibility\nJavaFX");
    }

    public static class ItemPedidoView {
        private final SimpleStringProperty nombre;
        private final SimpleDoubleProperty precioUnitario;
        private final SimpleIntegerProperty cantidadEnPedido;
        private final SimpleIntegerProperty stockDisponible;
        private final Producto productoOriginal;

        public ItemPedidoView(Producto producto, int cantidadEnPedido) {
            this.productoOriginal = producto;
            this.nombre = new SimpleStringProperty(producto.getNombre());
            this.precioUnitario = new SimpleDoubleProperty(producto.getPrecio());
            this.cantidadEnPedido = new SimpleIntegerProperty(cantidadEnPedido);
            this.stockDisponible = new SimpleIntegerProperty(producto.getStock()); // Muestra el stock actual del producto
        }

        public String getNombre() { return nombre.get(); }
        public double getPrecioUnitario() { return precioUnitario.get(); }
        public int getCantidadEnPedido() { return cantidadEnPedido.get(); }
        public int getStockDisponible() { return stockDisponible.get(); }
        public Producto getProductoOriginal() { return productoOriginal; }
    }
}