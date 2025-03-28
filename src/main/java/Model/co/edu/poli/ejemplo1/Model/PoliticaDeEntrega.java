package Model.co.edu.poli.ejemplo1.Model;

public class PoliticaDeEntrega {
    private int tiempoEstimado;
    private String condiciones;

    public PoliticaDeEntrega(int tiempoEstimado, String condiciones) {
        this.tiempoEstimado = tiempoEstimado;
        this.condiciones = condiciones;
    }

    @Override
    public String toString() {
        return "PoliticaDeEntrega{tiempoEstimado=" + tiempoEstimado + ", condiciones='" + condiciones + "'}";
    }
}
