package co.edu.poli.ejercicio.Model;

public class LimpiarPedidoComando implements Comando {
    private GestorPedido gestor;

    public LimpiarPedidoComando(GestorPedido gestor) {
        this.gestor = gestor;
    }

    @Override
    public void ejecutar() {
        gestor.limpiarPedido();
        // El mensaje de log ya se hace en GestorPedido
        System.out.println("Comando: Solicitud para limpiar el pedido (carrito) ejecutada.");
    }
}
