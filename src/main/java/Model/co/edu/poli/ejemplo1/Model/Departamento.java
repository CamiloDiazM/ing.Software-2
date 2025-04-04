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

    public List<Componente> getComponentes() {
        return componentes;
    }

    @Override
    public String mostrarDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("Departamento [").append(nombre).append("]\n");
        for (Componente componente : componentes) {
            sb.append("  ");
            sb.append(componente.mostrarDetalles());
        }
        return sb.toString();
    }
}