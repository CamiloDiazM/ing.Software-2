package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class Pagado implements EstadoPedido {

    @Override
    public void agregarProducto(Pedido pedido, Producto producto) {
        System.out.println("No puedes agregar productos a un pedido ya pagado.");

    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("No puedes cancelar un pedido ya pagado.");
    }

    @Override
    public void pagar(Pedido pedido) {
        System.out.println("El pedido ya está pagado.");
    }

    @Override
    public String getNombreEstado() {
        return "Pagado";
    }

}
