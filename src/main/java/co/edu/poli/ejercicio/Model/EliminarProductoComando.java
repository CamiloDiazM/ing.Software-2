package co.edu.poli.ejercicio.Model;

public class EliminarProductoComando implements Comando {
    private GestorPedido gestor;
    private Producto productoAEliminar;

    public EliminarProductoComando(GestorPedido gestor, Producto productoAEliminar) {
        this.gestor = gestor;
        this.productoAEliminar = productoAEliminar;
    }

    @Override
    public void ejecutar() {
        if (productoAEliminar != null) {
            gestor.eliminarProducto(productoAEliminar);
            // El mensaje de log ya se hace en GestorPedido, pero podemos añadir uno específico del comando
            System.out.println("Comando: Producto '" + productoAEliminar.getNombre() + "' solicitado para eliminación del pedido.");
        } else {
            System.out.println("Comando EliminarProducto: No se especificó un producto válido para eliminar.");
        }
    }
}
