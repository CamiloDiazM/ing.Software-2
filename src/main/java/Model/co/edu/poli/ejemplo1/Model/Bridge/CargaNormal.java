package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class CargaNormal implements TipoMercancia{
    private double peso;
    private double dimensiones;
    private String contenido;

    public CargaNormal(double peso, double dimensiones, String contenido) {
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.contenido = contenido;
    }

    @Override
    public String enviar(){
        return "Enviando carga normal " + contenido;
    }
}
