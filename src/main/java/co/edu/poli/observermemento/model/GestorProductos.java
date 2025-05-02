package co.edu.poli.observermemento.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorProductos {

    private List<Producto> productos;
    private Map<String, List<ObservadorProducto>> observadoresPorProducto;
    private HistorialPrecios historial;

    public GestorProductos() {
        this.productos = new ArrayList<>();
        this.observadoresPorProducto = new HashMap<>();
        this.historial = new HistorialPrecios();
    }

    public void registrarProducto(Producto producto) {
        productos.add(producto);
        producto.setGestorProductos(this);

        // Guardar estado inicial en el historial
        historial.guardarMemento(producto.getNombre(), producto.crearMemento());
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        observadoresPorProducto.remove(producto.getNombre());
    }

    public void cambiarPrecioProducto(String nombreProducto, double nuevoPrecio) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
                producto.setPrecio(nuevoPrecio);
                // Guardar el nuevo estado en el historial
                historial.guardarMemento(nombreProducto, producto.crearMemento());
                break;
            }
        }
    }

    public void suscribirObservador(String nombreProducto, ObservadorProducto observador) {
        if (!observadoresPorProducto.containsKey(nombreProducto)) {
            observadoresPorProducto.put(nombreProducto, new ArrayList<>());
        }
        observadoresPorProducto.get(nombreProducto).add(observador);
    }

    public void cancelarSuscripcion(String nombreProducto, ObservadorProducto observador) {
        if (observadoresPorProducto.containsKey(nombreProducto)) {
            observadoresPorProducto.get(nombreProducto).remove(observador);
        }
    }

    public List<ObservadorProducto> getObservadores(String nombreProducto) {
        return observadoresPorProducto.getOrDefault(nombreProducto, new ArrayList<>());
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public HistorialPrecios getHistorialPrecios() {
        return historial;
    }

    public void mostrarHistorialProducto(String nombreProducto) {
        System.out.println("Historial de precios para: " + nombreProducto);
        List<Producto.ProductoMemento> historialProducto = historial.getHistorialProducto(nombreProducto);
        for (int i = 0; i < historialProducto.size(); i++) {
            Producto.ProductoMemento memento = historialProducto.get(i);
            System.out.println("  [" + i + "] " + memento);
        }
    }

    public void restaurarPrecioProducto(String nombreProducto, int indiceHistorial) {
        List<Producto.ProductoMemento> historialProducto = historial.getHistorialProducto(nombreProducto);
        if (historialProducto != null && indiceHistorial >= 0 && indiceHistorial < historialProducto.size()) {
            Producto.ProductoMemento memento = historialProducto.get(indiceHistorial);
            for (Producto producto : productos) {
                if (producto.getNombre().equals(nombreProducto)) {
                    producto.restaurarDesdeMemento(memento);
                    System.out.println("Precio de " + nombreProducto + " restaurado a: " + memento.getPrecio());
                    break;
                }
            }
        } else {
            System.out.println("No se pudo restaurar el precio. Índice de historial inválido.");
        }
    }
}
