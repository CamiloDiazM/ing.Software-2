package Model.co.edu.poli.ejemplo1.Model.Builder;

public class Certificacion {
    private String tipo;
    private String fecha;

    public Certificacion(String tipo, String fecha) {
        this.tipo = tipo;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Certificacion{tipo='" + tipo + "', fecha='" + fecha + "'}";
    }
}
