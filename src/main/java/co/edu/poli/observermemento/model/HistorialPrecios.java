package co.edu.poli.observermemento.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorialPrecios {
    private Map<String, List<Producto.ProductoMemento>> historialPorProducto;

    public HistorialPrecios() {
        historialPorProducto = new HashMap<>();
    }

    public void guardarMemento(String nombreProducto, Producto.ProductoMemento memento) {
        if (!historialPorProducto.containsKey(nombreProducto)) {
            historialPorProducto.put(nombreProducto, new ArrayList<>());
        }
        historialPorProducto.get(nombreProducto).add(memento);
    }

    public List<Producto.ProductoMemento> getHistorialProducto(String nombreProducto) {
        return historialPorProducto.getOrDefault(nombreProducto, new ArrayList<>());
    }

    public Producto.ProductoMemento getUltimoMemento(String nombreProducto) {
        List<Producto.ProductoMemento> historial = historialPorProducto.get(nombreProducto);
        if (historial != null && !historial.isEmpty()) {
            return historial.get(historial.size() - 1);
        }
        return null;
    }

    public Producto.ProductoMemento getMementoPorFecha(String nombreProducto, long timestamp) {
        List<Producto.ProductoMemento> historial = historialPorProducto.get(nombreProducto);
        if (historial != null) {
            for (Producto.ProductoMemento memento : historial) {
                if (memento.getTimestamp() == timestamp) {
                    return memento;
                }
            }
        }
        return null;
    }
}
