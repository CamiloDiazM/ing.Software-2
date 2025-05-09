package co.edu.poli.ejercicio.Model;

public class AgregarProductosComando implements Comando {
    private GestorPedido gestor;
    private Producto producto;
    private int cantidad;

    public AgregarProductosComando(GestorPedido gestor, Producto producto, int cantidad) {
        this.gestor = gestor;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() {
        gestor.agregarProducto(producto, cantidad);
        System.out.println("Producto agregado desde comando: " + producto.getNombre() + " x" + cantidad);
    }
}
