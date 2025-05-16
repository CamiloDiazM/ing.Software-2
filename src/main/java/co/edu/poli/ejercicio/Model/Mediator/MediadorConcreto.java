package co.edu.poli.ejercicio.Model.Mediator;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

import java.util.ArrayList;
import java.util.List;

public class MediadorConcreto implements Mediator {

    private List<Producto> productos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }


    @Override
    public Pedido crearPedido(Cliente cliente) {
        return new Pedido(cliente);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}