package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class Pidiendo implements EstadoPedido {

    @Override
    public void agregarProducto(Pedido pedido, Producto producto) {
        pedido.getProductos().add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("Pedido cancelado.");
        pedido.setEstado(new Cancelado());
    }

    @Override
    public void pagar(Pedido pedido) {
        if (pedido.getProductos().isEmpty()) {
            System.out.println("No puedes pagar un pedido vacío.");
        } else {
            System.out.println("Pedido pagado.");
            pedido.setEstado(new Pagado());
        }
    }

    @Override
    public String getNombreEstado() {
        return "Pidiendo";
    }

}
