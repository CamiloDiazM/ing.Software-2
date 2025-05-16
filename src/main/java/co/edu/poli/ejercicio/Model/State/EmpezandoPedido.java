package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class EmpezandoPedido implements EstadoPedido {

    @Override
    public String agregarProducto(Pedido pedido, Producto producto) {
        // La lógica de agregar el producto a la lista del pedido se haría
        // antes o después de esta llamada, usualmente en el Mediador o Pedido.
        // Aquí, el estado se encarga de la transición y el mensaje.
        // Asumimos que el producto YA FUE AÑADIDO a pedido.getProductos() por el mediador.
        pedido.setEstado(new Pidiendo());
        return "Producto '" + producto.getNombre() + "' agregado. Estado del pedido: Pidiendo.";
    }

    @Override
    public String pagar(Pedido pedido) {
        // No se puede pagar directamente desde "Empezando Pedido" si no hay productos.
        // Si el pedido está vacío, la lógica de "Pidiendo.pagar()" también lo manejaría.
        return "No se puede pagar en este estado. Agregue productos primero.";
    }

    @Override
    public String cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado());
        return "Pedido cancelado mientras se estaba empezando.";
    }

    @Override
    public String getNombreEstado() {
        return "Empezando Pedido";
    }
}