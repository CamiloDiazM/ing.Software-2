package co.edu.poli.ejercicio.Model.Visitor;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class VisitorImpl implements Visitante {

    @Override
    public String visitar(Producto producto) {
        return "Producto: " + producto.getNombre() + ", Precio: $" + producto.getPrecio();
    }

    @Override
    public String visitar(Pedido pedido) {
        double total = 0;
        for (Producto producto : pedido.getProductos()) {
            System.out.println("- " + producto.getNombre() + ", Precio: $" + producto.getPrecio());
            total += producto.getPrecio();
        }
        return "$" + total;
    }

}
