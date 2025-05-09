package co.edu.poli.ejercicio.Model;

public interface Handler {
    void setNext(Handler next);

    void handleRequest(Pedido pedido);
}
