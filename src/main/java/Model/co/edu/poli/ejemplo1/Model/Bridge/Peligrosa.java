package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class Peligrosa implements TipoMercancia{

    private String contenido;
    private String precauciones;

    public Peligrosa(String contenido, String precauciones) {
        this.contenido = contenido;
        this.precauciones = precauciones;
    }

    @Override
    public String enviar() {
        return "Enviando mercancia peligrosa: " + contenido + " con precauciones: " + precauciones;
    }
}
