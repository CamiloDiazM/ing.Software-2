package co.edu.poli.ejercicio.Model.Visitor;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public interface Visitante {

    String visitar(Producto producto);

    String visitar(Pedido pedido);

}
