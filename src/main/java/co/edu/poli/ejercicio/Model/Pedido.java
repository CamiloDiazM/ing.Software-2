package co.edu.poli.ejercicio.Model;

import java.util.HashMap;
import java.util.Map;

public class Pedido {

    private Cliente cliente;
    private Map<Producto, Integer> productos;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new HashMap<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    public void setProductos(Map<Producto, Integer> productos) {
        this.productos = productos;
    }
}
