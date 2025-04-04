package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class Documentos implements TipoMercancia {
    private String descripcionDoc;
    private String numeroPaginas;

    public Documentos(String descripcionDoc, String numeroPaginas) {
        this.descripcionDoc = descripcionDoc;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String enviar(){
        return "Enviando documentos " + descripcionDoc;
    }

}
