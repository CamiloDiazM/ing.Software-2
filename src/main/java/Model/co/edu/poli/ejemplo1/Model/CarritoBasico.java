// src/main/java/Model/co/edu/poli/ejemplo1/Model/CarritoBasico.java
package Model.co.edu.poli.ejemplo1.Model;

import java.util.ArrayList;
import java.util.List;

public class CarritoBasico implements Carrito {
    private List<Double> precios;

    public CarritoBasico() {
        precios = new ArrayList<>();
        precios.add(10.0);
        precios.add(20.0);
        precios.add(30.0);
        precios.add(40.0);
        precios.add(50.0);
        precios.add(60.0);
        precios.add(70.0);
        precios.add(80.0);
        precios.add(90.0);
        precios.add(100.0);
    }

    public double obtenerPrecio() {
        return precios.stream().mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public String obtenerDescripcion() {
        return "Bonos";
    }

    @Override
    public boolean tieneEnvioGratis() {
        return false;
    }

    @Override
    public int obtenerPuntos() {
        return 0; // No se acumulan puntos en el carrito básico
    }
}