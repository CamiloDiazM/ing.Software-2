package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public interface EstadoPedido {

    void agregarProducto(Pedido pedido, Producto producto);

    void pagar(Pedido pedido);

    void cancelar(Pedido pedido);

    String getNombreEstado();

}
