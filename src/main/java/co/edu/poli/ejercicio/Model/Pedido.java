package co.edu.poli.ejercicio.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.poli.ejercicio.Model.State.EmpezandoPedido;
import co.edu.poli.ejercicio.Model.State.EstadoPedido;
import co.edu.poli.ejercicio.Model.Visitor.ClaseVisitable;
import co.edu.poli.ejercicio.Model.Visitor.Visitante;

public class Pedido implements ClaseVisitable {

    private Cliente cliente;
    private List<Producto> productos;
    private EstadoPedido estado;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.estado = new EmpezandoPedido();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    @Override
    public String aceptar(Visitante visitor) {
        return visitor.visitar(this);
    }
}
