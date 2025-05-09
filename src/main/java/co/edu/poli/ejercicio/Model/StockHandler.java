package co.edu.poli.ejercicio.Model;

import java.util.Map;

public class StockHandler extends HandlerBase {

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(Pedido pedido) {
        boolean stockSuficiente = true;

        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();

            if (producto.getStock() < cantidad) {
                System.out.println("No hay suficiente stock para el producto: " + producto.getNombre());
                stockSuficiente = false;
            }
        }

        if (stockSuficiente) {
            System.out.println("Stock suficiente para procesar el pedido.");
            if (next != null) {
                next.handleRequest(pedido);
            }
        } else {
            System.out.println("No se puede procesar el pedido debido a falta de stock.");
        }
    }

}
