package co.edu.poli.ejercicio.Model.State;

import co.edu.poli.ejercicio.Model.Pedido;
import co.edu.poli.ejercicio.Model.Producto;

public class Pagado implements EstadoPedido {

    @Override
    public String agregarProducto(Pedido pedido, Producto producto) {
        return "No puedes agregar productos a un pedido ya pagado.";
    }

    @Override
    public String cancelar(Pedido pedido) {
        // Dependiendo de las reglas de negocio, un pedido pagado podría o no cancelarse
        // (y si se cancela, podría requerir un proceso de reembolso).
        // Por simplicidad, aquí no permitimos la cancelación directa de un pedido pagado.
        // O se podría introducir un estado "Reembolsado" o "CanceladoPostPago".
        return "No puedes cancelar un pedido que ya ha sido pagado.";
        // Alternativa:
        // pedido.setEstado(new Cancelado()); // O un estado específico
        // return "Pedido pagado ha sido marcado para cancelación/reembolso.";
    }

    @Override
    public String pagar(Pedido pedido) {
        return "El pedido ya está pagado.";
    }

    @Override
    public String getNombreEstado() {
        return "Pagado";
    }
}