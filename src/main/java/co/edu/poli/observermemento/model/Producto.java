package co.edu.poli.observermemento.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producto {

    private String nombre;
    private String descripcion;
    private double precioActual;
    private long ultimaActualizacion;
    private List<ObservadorProducto> observadores;
    private GestorProductos gestorProductos;

    public Producto(String nombre, String descripcion, double precioInicial) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioActual = precioInicial;
        this.ultimaActualizacion = System.currentTimeMillis();
        this.observadores = new ArrayList<>();
    }
    public void setGestorProductos(GestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
    }

    public void agregarObservador(ObservadorProducto observador) {
        observadores.add(observador);
    }
    public List<ObservadorProducto> getObservadores() {
        return observadores;
    }

    public void eliminarObservador(ObservadorProducto observador) {
        observadores.remove(observador);
    }

    public void setPrecio(double nuevoPrecio) {
        double precioAnterior = this.precioActual;
        this.precioActual = nuevoPrecio;
        this.ultimaActualizacion = System.currentTimeMillis();

        // Notificar a los observadores sobre el cambio de precio
        notificarObservadores(precioAnterior, nuevoPrecio);
    }

    private void notificarObservadores(double precioAnterior, double precioNuevo) {
        for (ObservadorProducto observador : observadores) {
            observador.actualizar(this, precioAnterior, precioNuevo);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precioActual;
    }

    public long getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    // Crear un memento con el estado actual
    public ProductoMemento crearMemento() {
        return new ProductoMemento(precioActual, ultimaActualizacion);
    }

    // Restaurar estado desde un memento
    public void restaurarDesdeMemento(ProductoMemento memento) {
        this.precioActual = memento.getPrecio();
        this.ultimaActualizacion = memento.getTimestamp();
    }

    // Inner class ProductoMemento
    public class ProductoMemento {
        private final double precio;
        private final long timestamp;

        private ProductoMemento(double precio, long timestamp) {
            this.precio = precio;
            this.timestamp = timestamp;
        }

        public double getPrecio() {
            return precio;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getNombreProducto() {
            return Producto.this.nombre; // Acceder al nombre del producto exterior
        }

        @Override
        public String toString() {
            return "Precio: " + precio + ", Fecha: " + new Date(timestamp);
        }
    }
}