package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class Pidiendo implements EstadoPedido {

    @Override
    public String agregarProducto(Pedido pedido, Producto producto) {
        // Asumimos que el producto YA FUE AÑADIDO a pedido.getProductos() por el mediador.
        // El estado "Pidiendo" se mantiene, solo se confirma la adición.
        return "Producto '" + producto.getNombre() + "' agregado al pedido.";
    }

    @Override
    public String cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado());
        return "Pedido cancelado.";
    }

    @Override
    public String pagar(Pedido pedido) {
        if (pedido.getProductos().isEmpty()) {
            // El estado no cambia si no se puede pagar.
            return "No puedes pagar un pedido vacío.";
        } else {
            pedido.setEstado(new Pagado());
            return "Pedido pagado correctamente.";
        }
    }

    @Override
    public String getNombreEstado() {
        return "Pidiendo";
    }
}