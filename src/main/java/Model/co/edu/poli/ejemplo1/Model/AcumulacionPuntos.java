// src/main/java/Model/co/edu/poli/ejemplo1/Model/AcumulacionPuntos.java
package Model.co.edu.poli.ejemplo1.Model;

public class AcumulacionPuntos extends CarritoDecorator {
    private int puntos;

    public AcumulacionPuntos(Carrito carrito, int puntos) {
        super(carrito);
        this.puntos = puntos;
    }

    @Override
    public double obtenerPrecio() {
        double precioBase = super.obtenerPrecio();
        return precioBase - puntos;
    }

    @Override
    public int obtenerPuntos() {
        return puntos;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " con " + puntos + " puntos.";
    }
}