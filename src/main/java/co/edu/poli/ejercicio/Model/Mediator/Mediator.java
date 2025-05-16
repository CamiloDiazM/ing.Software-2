package co.edu.poli.ejercicio.Model.Mediator;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;
import co.edu.poli.ejercicio.Model.Visitor.ClaseVisitable;

public interface Mediator {
    String crearPedido(Cliente cliente);

    String agregarProductoAlPedido(Cliente cliente, Producto producto);

    void eliminarProductoDelPedido(Cliente cliente, Producto producto);

    void aplicarDescuento(Cliente cliente, double descuento);

    Pedido obtenerPedido(Cliente cliente);
}
