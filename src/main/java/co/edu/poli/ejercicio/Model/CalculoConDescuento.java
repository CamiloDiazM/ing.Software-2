package co.edu.poli.ejercicio.Model;

import java.util.Map;

public class CalculoConDescuento implements CalculoTotalStrategy {

    @Override
    public double calcularTotal(Pedido pedido) {
        double total = 0.0;

        for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            total += producto.getPrecio() * cantidad;
        }

        total *= 0.9; // Aplica un 10% de descuento

        return total;
    }

}
