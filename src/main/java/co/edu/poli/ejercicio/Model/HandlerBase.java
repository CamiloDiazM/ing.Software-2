package co.edu.poli.ejercicio.Model;

public class HandlerBase implements Handler {

    protected Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(Pedido pedido) {
        if (next != null)
            next.handleRequest(pedido);
    }

}
