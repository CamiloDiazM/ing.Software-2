package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public interface EstadoPedido {

    String agregarProducto(Pedido pedido, Producto producto);

    String pagar(Pedido pedido);

    String cancelar(Pedido pedido);

    String getNombreEstado(); // Este puede seguir igual, ya que solo devuelve el nombre.

}
