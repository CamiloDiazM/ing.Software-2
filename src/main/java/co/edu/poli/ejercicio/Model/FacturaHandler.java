package co.edu.poli.ejercicio.Model;

import java.util.Map;

public class FacturaHandler extends HandlerBase {

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(Pedido pedido) {
        GestorPedido gestor = new GestorPedido(pedido);
        System.out.println("Generando factura para el pedido:");
        System.out.println("Productos:");
        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("- " + producto.getNombre() + ": " + cantidad);
        }
        System.out.println("Total: $" + gestor.calcularTotal());

        if (next != null) {
            next.handleRequest(pedido);
        }
    }

}
