package co.edu.poli.ejercicio.Model;

import java.util.Map;

public class GestorPedido {
    private Pedido pedido;
    private CalculoTotalStrategy estrategia;
    private Handler handlerInicial;

    public GestorPedido(Pedido pedido) {
        this.pedido = pedido;
        this.estrategia = new CalculoSinDescuento(); // Por defecto
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.getStock() >= cantidad) {
            Map<Producto, Integer> productos = pedido.getProductos();
            productos.put(producto, productos.getOrDefault(producto, 0) + cantidad);
            System.out.println("Producto agregado al pedido: " + producto.getNombre() + " x" + cantidad);
        } else {
            System.out.println("Stock insuficiente para agregar " + producto.getNombre() + " al pedido.");
            // Idealmente, lanzar una excepción o devolver un indicador de fallo
        }
    }

    // --- NUEVO MÉTODO ---
    public void eliminarProducto(Producto productoAEliminar) {
        if (productoAEliminar == null) {
            System.out.println("Error: Intento de eliminar un producto nulo del pedido.");
            return;
        }
        if (pedido.getProductos().containsKey(productoAEliminar)) {
            pedido.getProductos().remove(productoAEliminar);
            System.out.println("Producto '" + productoAEliminar.getNombre() + "' eliminado del pedido.");
        } else {
            System.out.println("El producto '" + productoAEliminar.getNombre() + "' no se encontró en el pedido para eliminarlo.");
        }
    }

    // --- NUEVO MÉTODO ---
    public void limpiarPedido() {
        if (pedido != null && pedido.getProductos() != null) {
            pedido.getProductos().clear();
            System.out.println("El carrito de compras (pedido actual) ha sido vaciado.");
        }
    }

    public double calcularTotal() {
        return estrategia.calcularTotal(pedido);
    }

    public void reducirStock() {
        if (pedido.getProductos().isEmpty()) {
            System.out.println("No hay productos en el pedido para reducir stock.");
            return;
        }
        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidadPedida = entry.getValue();
            if (producto.getStock() >= cantidadPedida) {
                producto.setStock(producto.getStock() - cantidadPedida);
                System.out.println("Stock reducido para '" + producto.getNombre() + "'. Nuevo stock: " + producto.getStock());
            } else {
                System.out.println("Stock insuficiente para reducir la cantidad pedida de '" + producto.getNombre() + "'. Stock actual: " + producto.getStock() + ", Pedido: " + cantidadPedida);
                // Considerar cómo manejar esta situación crítica. ¿Parar el proceso?
            }
        }
    }

    public void procesarPedido() {
        if (pedido.getProductos().isEmpty()) {
            System.out.println("No se puede procesar un pedido vacío.");
            // En el controller se hará un log y alerta
            return;
        }
        if (handlerInicial != null) {
            System.out.println("Iniciando procesamiento del pedido a través de la cadena de responsabilidad...");
            handlerInicial.handleRequest(pedido);
            // La decisión de limpiar el carrito se tomará en el controller DESPUÉS de esta llamada.
        } else {
            System.out.println("Error: No hay manejadores configurados para procesar el pedido.");
        }
    }

    public void setEstrategia(CalculoTotalStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public void setHandlerInicial(Handler handler) {
        this.handlerInicial = handler;
    }

    public Pedido getPedido() {
        return pedido;
    }
}