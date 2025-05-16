package co.edu.poli.ejercicio.Model.Mediator;

import java.util.HashMap;
import java.util.Map;

import co.edu.poli.ejercicio.Model.Cliente;
import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;
import co.edu.poli.ejercicio.Model.Visitor.ClaseVisitable;

public class MediadorConcreto implements Mediator {
    private Map<Cliente, Pedido> pedidos = new HashMap<>();

    public String crearPedido(Cliente cliente) {
        Pedido pedido = new Pedido(cliente);
        pedidos.put(cliente, pedido);
        return "Pedido creado para: " + cliente.getNombre();
    }

    public String agregarProductoAlPedido(Cliente cliente, Producto producto) {
        Pedido pedido = pedidos.get(cliente);
        if (pedido != null) {
            pedido.getProductos().add(producto);

        }
        return "Producto agregado al pedido de " + cliente.getNombre() + ": " + producto.getNombre();
    }

    public void eliminarProductoDelPedido(Cliente cliente, Producto producto) {
        Pedido pedido = pedidos.get(cliente);
        if (pedido != null) {
            pedido.getProductos().remove(producto);

        }
    }

    public void aplicarDescuento(Cliente cliente, double descuento) {
        Pedido pedido = pedidos.get(cliente);
        if (pedido != null) {
            double total = 0;
            for (Producto producto : pedido.getProductos()) {
                total += producto.getPrecio();
            }
            double totalConDescuento = total - (total * descuento);
            System.out.println("Total con descuento: $" + totalConDescuento);
        }
    }

    public Map<Cliente, Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Map<Cliente, Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public Pedido obtenerPedido(Cliente cliente) {
        return pedidos.get(cliente);
    }

}
