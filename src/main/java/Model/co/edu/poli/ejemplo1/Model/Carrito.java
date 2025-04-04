// src/main/java/Model/co/edu/poli/ejemplo1/Model/Carrito.java
package Model.co.edu.poli.ejemplo1.Model;

public interface Carrito {
    double obtenerPrecio();
    String obtenerDescripcion();
    boolean tieneEnvioGratis();
    int obtenerPuntos();
}