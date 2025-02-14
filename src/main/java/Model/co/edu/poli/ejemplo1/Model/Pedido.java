package Model.co.edu.poli.ejemplo1.Model;

import java.util.List;

public class Pedido {

    private String numPedido;

    private String fecha;

    private Cliente cliente;

    private List <Producto> producto;

    public Pedido(String numPedido, Cliente cliente, String fecha, List<Producto> producto) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.fecha = fecha;
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numPedido='" + numPedido + '\'' +
                ", fecha='" + fecha + '\'' +
                ", cliente=" + cliente +
                ", producto=" + producto +
                '}';
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

}