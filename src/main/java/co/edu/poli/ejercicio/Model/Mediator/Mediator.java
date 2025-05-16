package co.edu.poli.ejercicio.Model.Mediator;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;
import java.util.List;

public interface Mediator {
    void agregarProducto(Producto producto);
    Pedido crearPedido(Cliente cliente);
    List<Producto> getProductos();
    List<Cliente> getClientes();
}