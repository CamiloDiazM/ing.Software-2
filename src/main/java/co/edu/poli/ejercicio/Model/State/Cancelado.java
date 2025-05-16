package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class Cancelado implements EstadoPedido {

    @Override
    public String agregarProducto(Pedido pedido, Producto producto) {
        // Si se quiere reabrir un pedido cancelado al agregar un producto:
        // pedido.getProductos().clear(); // Opcional: limpiar productos anteriores
        // pedido.getProductos().add(producto);
        // pedido.setEstado(new Pidiendo());
        // return "Pedido reabierto. Producto '" + producto.getNombre() + "' agregado.";
        return "No se pueden agregar productos a un pedido cancelado. Inicie un nuevo pedido.";
    }

    @Override
    public String cancelar(Pedido pedido) {
        return "El pedido ya está cancelado.";
    }

    @Override
    public String pagar(Pedido pedido) {
        return "No se puede pagar un pedido cancelado.";
    }

    @Override
    public String getNombreEstado() {
        return "Cancelado";
    }
}