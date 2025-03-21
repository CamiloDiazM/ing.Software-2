package Model.co.edu.poli.ejemplo1.Model;

import java.util.ArrayList;
import java.util.List;

public class Departamento implements Componente {
    private String nombre;
    private List<Componente> componentes;

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.componentes = new ArrayList<>();
    }

    public void agregarComponente(Componente componente) {
        componentes.add(componente);
    }

    public void eliminarComponente(Componente componente) {
        componentes.remove(componente);
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    @Override
    public void mostrarDetalles(StringBuilder sb) {
        sb.append("Departamento [Nombre=").append(nombre).append("]\n");
        for (Componente componente : componentes) {
            sb.append("  ");
            componente.mostrarDetalles(sb);
        }
    }
}