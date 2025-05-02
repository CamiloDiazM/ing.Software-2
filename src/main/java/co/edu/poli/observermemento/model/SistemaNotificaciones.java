package co.edu.poli.observermemento.model;

public class SistemaNotificaciones implements ObservadorProducto{

    @Override
    public void actualizar(Producto producto, double precioAnterior, double precioNuevo) {
        if (precioNuevo > precioAnterior) {
            enviarNotificacionSubidaPrecio(producto, precioAnterior, precioNuevo);
        } else if (precioNuevo < precioAnterior) {
            enviarNotificacionBajadaPrecio(producto, precioAnterior, precioNuevo);
        }
    }

    public void enviarNotificacionSubidaPrecio(Producto producto, double precioAnterior, double precioNuevo) {
        System.out.println("SISTEMA: Notificación de subida de precio para " + producto.getNombre());
        System.out.println("  Precio anterior: " + precioAnterior);
        System.out.println("  Precio nuevo: " + precioNuevo);
        System.out.println("  Incremento: " + (precioNuevo - precioAnterior));
    }

    public void enviarNotificacionBajadaPrecio(Producto producto, double precioAnterior, double precioNuevo) {
        System.out.println("SISTEMA: Notificación de bajada de precio para " + producto.getNombre());
        System.out.println("  Precio anterior: " + precioAnterior);
        System.out.println("  Precio nuevo: " + precioNuevo);
        System.out.println("  Reducción: " + (precioAnterior - precioNuevo));
    }
}
