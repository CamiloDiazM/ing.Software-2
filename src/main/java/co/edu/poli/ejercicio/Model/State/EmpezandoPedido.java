package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class EmpezandoPedido implements EstadoPedido {

    @Override
    public void agregarProducto(Pedido pedido, Producto producto) {
        pedido.getProductos().add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
        pedido.setEstado(new Pidiendo());
    }

    @Override
    public void pagar(Pedido pedido) {
        System.out.println("No se puede pagar en este estado. Agregue productos primero.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("Pedido cancelado.");
        pedido.setEstado(new Cancelado());
    }

    @Override
    public String getNombreEstado() {
        return "Empezando Pedido";
    }

}
