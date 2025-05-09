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

    // 👉 Command lo llama para agregar productos
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.getStock() >= cantidad) {
            Map<Producto, Integer> productos = pedido.getProductos();
            productos.put(producto, productos.getOrDefault(producto, 0) + cantidad);
            System.out.println("Producto agregado: " + producto.getNombre() + " x" + cantidad);
        } else {
            System.out.println("Stock insuficiente para " + producto.getNombre());
        }
    }

    // 👉 Strategy
    public double calcularTotal() {
        return estrategia.calcularTotal(pedido);
    }

    // 👉 Command lo llama para reducir el stock
    public void reducirStock() {
        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            producto.setStock(producto.getStock() - cantidad);
        }
    }

    // 👉 Chain of Responsibility
    public void procesarPedido() {
        if (handlerInicial != null) {
            handlerInicial.handleRequest(pedido);
        } else {
            System.out.println("No hay manejadores para procesar el pedido.");
        }
    }

    // Setter para Strategy
    public void setEstrategia(CalculoTotalStrategy estrategia) {
        this.estrategia = estrategia;
    }

    // Setter para Chain of Responsibility
    public void setHandlerInicial(Handler handler) {
        this.handlerInicial = handler;
    }

    // Getter por si otros comandos lo necesitan
    public Pedido getPedido() {
        return pedido;
    }

    public Handler getHandlerInicial() {
        return handlerInicial;
    }
}
