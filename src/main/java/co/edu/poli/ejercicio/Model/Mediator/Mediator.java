package co.edu.poli.ejercicio.Model.Mediator;

import java.util.List;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public interface Mediator {

    String crearProducto(String nombre, double precio);

    String eliminarProducto(String nombre);

    String crearCliente(String nombre, String id);

    List<Cliente> obtenerClientes();

    List<Producto> obtenerProductos();
}
