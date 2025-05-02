package co.edu.poli.observermemento.model;

public class Cliente implements ObservadorProducto {

    private String nombre;
    private String email;
    private boolean notificarSubidas;
    private boolean notificarBajadas;

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.notificarSubidas = true;  // Por defecto, notificar ambos cambios
        this.notificarBajadas = true;
    }

    public void setNotificaciones(boolean notificarSubidas, boolean notificarBajadas) {
        this.notificarSubidas = notificarSubidas;
        this.notificarBajadas = notificarBajadas;
    }

    @Override
    public void actualizar(Producto producto, double precioAnterior, double precioNuevo) {
        if (precioNuevo < precioAnterior && notificarBajadas) {
            notificarBajadaPrecio(producto, precioAnterior, precioNuevo);
        } else if (precioNuevo > precioAnterior && notificarSubidas) {
            notificarSubidaPrecio(producto, precioAnterior, precioNuevo);
        }
    }

    private void notificarBajadaPrecio(Producto producto, double precioAnterior, double precioNuevo) {
        System.out.println("Cliente " + nombre + " (" + email + ") notificado sobre bajada de precio");
        System.out.println("  Producto: " + producto.getNombre());
        System.out.println("  ¡Buenas noticias! El precio ha bajado de " + precioAnterior +
                " a " + precioNuevo);
        System.out.println("  Ahorro: " + (precioAnterior - precioNuevo));
    }

    private void notificarSubidaPrecio(Producto producto, double precioAnterior, double precioNuevo) {
        System.out.println("Cliente " + nombre + " (" + email + ") notificado sobre subida de precio");
        System.out.println("  Producto: " + producto.getNombre());
        System.out.println("  El precio ha aumentado de " + precioAnterior +
                " a " + precioNuevo);
        System.out.println("  Incremento: " + (precioNuevo - precioAnterior));
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}