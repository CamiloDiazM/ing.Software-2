// src/main/java/Model/co/edu/poli/ejemplo1/Model/EnvioGratis.java
package Model.co.edu.poli.ejemplo1.Model;

public class EnvioGratis extends CarritoDecorator {
    private static final double COSTO_ENVIO = 15.0;

    public EnvioGratis(Carrito carrito) {
        super(carrito);
    }

    @Override
    public double obtenerPrecio() {
        return super.obtenerPrecio() - COSTO_ENVIO;
    }

    @Override
    public boolean tieneEnvioGratis() {
        return true;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " con envío gratis.";
    }
}