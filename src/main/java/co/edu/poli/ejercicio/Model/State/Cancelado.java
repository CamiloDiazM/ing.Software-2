package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class Cancelado implements EstadoPedido {

    @Override
    public void agregarProducto(Pedido pedido, Producto producto) {
        pedido.setEstado(new EmpezandoPedido());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("El pedido ya está cancelado.");
    }

    @Override
    public void pagar(Pedido pedido) {
        System.out.println("No se puede pagar un pedido cancelado.");
    }

    @Override
    public String getNombreEstado() {
        return "Cancelado";
    }

}
