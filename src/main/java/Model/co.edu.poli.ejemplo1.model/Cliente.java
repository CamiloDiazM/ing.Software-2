package Model.co.edu.poli.ejemplo1.model;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Cliente {

    public Cliente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    private String id;

    private String nombre;

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}