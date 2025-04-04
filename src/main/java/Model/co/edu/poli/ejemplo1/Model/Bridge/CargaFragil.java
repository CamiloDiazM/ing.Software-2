package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class CargaFragil implements TipoMercancia{
    private double peso;
    private String dimensiones;
    private String contenido;
    private String materialProteccion;

    public CargaFragil(double peso, String dimensiones, String contenido, String materialProteccion) {
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.contenido = contenido;
        this.materialProteccion = materialProteccion;
    }

    @Override
    public String enviar(){
        return "Enviando carga fragil: " + contenido + "con proteccion: " + materialProteccion;
    }
}
