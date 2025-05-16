package co.edu.poli.ejercicio.Model.Mediator;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;
import co.edu.poli.ejercicio.Model.State.EmpezandoPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MediadorConcreto implements Mediator {
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Producto> catalogoProductos = new ArrayList<>();
    private Pedido pedidoActual;

    public MediadorConcreto() {
        catalogoProductos.add(new Producto("Laptop", 1200000));
        catalogoProductos.add(new Producto("Mouse", 25000));
        catalogoProductos.add(new Producto("Teclado", 50000));
        catalogoProductos.add(new Producto("Monitor", 300000));

        listaClientes.add(new Cliente("Cliente Ejemplo 1", "C001"));
        listaClientes.add(new Cliente("Dana", "5"));
    }

    @Override
    public String crearProductoCatalogo(String nombre, double precio) {
        if (catalogoProductos.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre))) {
            return "Error: Ya existe un producto con el nombre '" + nombre + "'.";
        }
        catalogoProductos.add(new Producto(nombre, precio));
        return "Producto '" + nombre + "' agregado al catálogo.";
    }

    @Override
    public String eliminarProductoCatalogo(String nombre) {
        boolean removed = catalogoProductos.removeIf(p -> p.getNombre().equals(nombre));
        if (removed && pedidoActual != null) {
            pedidoActual.getProductos().removeIf(p -> p.getNombre().equals(nombre));
        }
        return removed ? "Producto '" + nombre + "' eliminado." : "Producto '" + nombre + "' no encontrado.";
    }

    @Override
    public List<Producto> obtenerProductosCatalogo() {
        return new ArrayList<>(catalogoProductos);
    }

    @Override
    public String crearCliente(String nombre, String id) {
        if (listaClientes.stream().anyMatch(c -> c.getId().equals(id))) {
            return "Error: Ya existe un cliente con el ID " + id + ".";
        }
        listaClientes.add(new Cliente(nombre, id));
        return "Cliente '" + nombre + "' creado.";
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return new ArrayList<>(listaClientes);
    }

    @Override
    public Cliente buscarClientePorId(String id) {
        return listaClientes.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Pedido iniciarNuevoPedido(String clienteId) {
        Cliente cliente = buscarClientePorId(clienteId);
        if (cliente == null) {
            return null;
        }
        pedidoActual = new Pedido(cliente);
        return pedidoActual;
    }

    @Override
    public String agregarProductoAPedidoActual(String nombreProducto) {
        if (pedidoActual == null) {
            return "Error: No hay un pedido activo.";
        }
        Optional<Producto> productoOpt = catalogoProductos.stream()
                .filter(p -> p.getNombre().equals(nombreProducto))
                .findFirst();
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            pedidoActual.getProductos().add(producto);
            pedidoActual.getEstado().agregarProducto(pedidoActual, producto);
            return "Producto '" + nombreProducto + "' agregado.";
        }
        return "Error: Producto '" + nombreProducto + "' no encontrado.";
    }

    @Override
    public String eliminarProductoDePedidoActual(String nombreProducto) {
        if (pedidoActual == null) {
            return "Error: No hay un pedido activo.";
        }
        boolean removed = pedidoActual.getProductos().removeIf(p -> p.getNombre().equals(nombreProducto));
        if (removed && pedidoActual.getProductos().isEmpty()) {
            pedidoActual.setEstado(new EmpezandoPedido());
        }
        return removed ? "Producto '" + nombreProducto + "' eliminado." : "Producto '" + nombreProducto + "' no encontrado.";
    }

    @Override
    public Pedido obtenerPedidoActual() {
        return pedidoActual;
    }

    @Override
    public String finalizarPedidoActual() {
        if (pedidoActual == null || pedidoActual.getProductos().isEmpty()) {
            return "Error: No se puede finalizar un pedido vacío.";
        }
        pedidoActual.getEstado().pagar(pedidoActual);
        pedidoActual = null;
        return "Pedido finalizado.";
    }

    @Override
    public String cancelarPedidoActual() {
        if (pedidoActual == null) {
            return "Error: No hay un pedido activo.";
        }
        pedidoActual.getEstado().cancelar(pedidoActual);
        return "Pedido cancelado.";
    }
}