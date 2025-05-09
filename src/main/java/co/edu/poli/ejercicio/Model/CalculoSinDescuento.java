package co.edu.poli.ejercicio.Model;

import java.util.Map;

public class CalculoSinDescuento implements CalculoTotalStrategy {

    @Override
    public double calcularTotal(Pedido pedido) {
        double total = 0.0;

        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            total += producto.getPrecio() * cantidad;
        }

        return total;
    }

}
