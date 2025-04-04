// src/main/java/Model/co/edu/poli/ejemplo1/Model/CarritoDecorator.java
package Model.co.edu.poli.ejemplo1.Model;

public abstract class CarritoDecorator implements Carrito {
    protected Carrito carrito;

    public CarritoDecorator(Carrito carrito) {
        this.carrito = carrito;
    }

    @Override
    public double obtenerPrecio() {
        return carrito.obtenerPrecio();
    }

    @Override
    public String obtenerDescripcion() {
        return carrito.obtenerDescripcion();
    }

    @Override
    public boolean tieneEnvioGratis() {
        return carrito.tieneEnvioGratis();
    }

    @Override
    public int obtenerPuntos() {
        return carrito.obtenerPuntos();
    }
}