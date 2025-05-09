package co.edu.poli.ejercicio.Model;

public class DescuentoHandler extends HandlerBase {

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(Pedido pedido) {
        GestorPedido gestor = new GestorPedido(pedido);
        if (gestor.calcularTotal() > 200000) {
            System.out.println("Se aplicó un descuento del 10%");
            double total = gestor.calcularTotal() * 0.9;
            System.out.println("Nuevo total con descuento: $" + total);
        } else {
            System.out.println("No se aplica descuento.");
        }

        if (next != null) {
            next.handleRequest(pedido);
        }
    }
}
