package Model.co.edu.poli.ejemplo1.Model.Builder;

public class Evaluacion {
    private int puntuacion;
    private String comentarios;

    public Evaluacion(int puntuacion, String comentarios) {
        this.puntuacion = puntuacion;
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Evaluacion{score=" + puntuacion + ", comentarios='" + comentarios + "'}";
    }
}
