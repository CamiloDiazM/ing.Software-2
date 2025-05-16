package co.edu.poli.ejercicio.Model.Mediator;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Mediator.Mediator;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

import java.util.ArrayList;
import java.util.List;

public class MediadorConcreto implements Mediator {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Producto> productosDisponibles = new ArrayList<>();

    public MediadorConcreto() {

        productosDisponibles.add(new Producto("Laptop", 1200000));
        productosDisponibles.add(new Producto("Mouse", 25000));
        productosDisponibles.add(new Producto("Teclado", 50000));
        productosDisponibles.add(new Producto("Monitor", 300000));
    }

    @Override
    public String crearProducto(String nombre, double precio) {
        Producto producto = new Producto(nombre, precio);
        productosDisponibles.add(producto);
        return "Producto creado: " + nombre;
    }

    @Override
    public String eliminarProducto(String nombre) {
        Producto producto = productosDisponibles.stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
        if (producto != null) {
            productosDisponibles.remove(producto);
            return "Producto eliminado: " + nombre;
        }
        return "Producto no encontrado.";
    }

    @Override
    public String crearCliente(String nombre, String id) {
        Cliente cliente = new Cliente(nombre, id);
        clientes.add(cliente);
        return "Cliente creado: " + nombre;
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productosDisponibles;
    }
}
