package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class EnvioInternacional extends Envio{

    private String paisDestino;
    private double costoImportacion;
    private String nombreTransportadora;

    public EnvioInternacional(TipoMercancia tipoMercancia, int idEnvio, String nombreDestinatario, String fechaEnvio, String fechaEntregaEstimada, String ciudadDestino, String direccionExacta, String paisDestino, double costoImportacion, String nombreTransportadora) {
        super(tipoMercancia, idEnvio, nombreDestinatario, fechaEnvio, fechaEntregaEstimada, ciudadDestino, direccionExacta);
        this.paisDestino = paisDestino;
        this.costoImportacion = costoImportacion;
        this.nombreTransportadora = nombreTransportadora;
    }
}
