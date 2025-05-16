package co.edu.poli.ejercicio.Model.Mediator;

import java.util.List;
import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido; // Importante para la nueva funcionalidad
import co.edu.poli.ejercicio.Model.Producto;

public interface Mediator {

    String crearProductoCatalogo(String nombre, double precio);
    String eliminarProductoCatalogo(String nombre);
    List<Producto> obtenerProductosCatalogo();
    String crearCliente(String nombre, String id);
    List<Cliente> obtenerClientes();
    Cliente buscarClientePorId(String id); // Nuevo método útil
    Pedido iniciarNuevoPedido(String clienteId);
    String agregarProductoAPedidoActual(String nombreProducto);
    String eliminarProductoDePedidoActual(String nombreProducto);
    Pedido obtenerPedidoActual();
    String finalizarPedidoActual();
    String cancelarPedidoActual();


}
