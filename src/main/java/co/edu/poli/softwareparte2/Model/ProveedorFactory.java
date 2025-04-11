package co.edu.poli.softwareparte2.Model;

import java.util.HashMap;
import java.util.Map;

public class ProveedorFactory  {

    private static final Map<String, Proveedor> proveedores = new HashMap<>();

    public static Proveedor getProveedor(String nombre) {
        if (!proveedores.containsKey(nombre)) {
            proveedores.put(nombre, new Proveedor(nombre));
        }
        return proveedores.get(nombre);
    }

    public static int getTotalProveedores() {
        return proveedores.size();
    }
}
