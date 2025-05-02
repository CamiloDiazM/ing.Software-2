package co.edu.poli.observermemento.model;

public interface ObservadorProducto {
    void actualizar(Producto producto, double precioAnterior, double precioNuevo);

}
