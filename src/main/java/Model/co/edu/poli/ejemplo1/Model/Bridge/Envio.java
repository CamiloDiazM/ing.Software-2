package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class Envio {

    protected TipoMercancia tipoMercancia;
    protected int idEnvio;
    protected String nombreDestinatario;
    protected String fechaEnvio;
    protected String fechaEntregaEstimada;
    protected String ciudadDestino;
    protected String direccionExacta;

    public Envio(TipoMercancia tipoMercancia, int idEnvio, String nombreDestinatario, String fechaEnvio, String fechaEntregaEstimada, String ciudadDestino, String direccionExacta) {
        this.tipoMercancia = tipoMercancia;
        this.idEnvio = idEnvio;
        this.nombreDestinatario = nombreDestinatario;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.ciudadDestino = ciudadDestino;
        this.direccionExacta = direccionExacta;
    }

    public String realizarEnvio() {
        return "Envío realizado a " + nombreDestinatario + " con tipo de mercancía: " + tipoMercancia.enviar();
    }

}
