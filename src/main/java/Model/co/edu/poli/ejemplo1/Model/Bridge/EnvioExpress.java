package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class EnvioExpress extends Envio {

    private String costoEnvioRapido;

    public EnvioExpress(TipoMercancia tipoMercancia, int idEnvio, String nombreDestinatario, String fechaEnvio, String fechaEntregaEstimada, String ciudadDestino, String direccionExacta, String costoEnvioRapido) {
        super(tipoMercancia, idEnvio, nombreDestinatario, fechaEnvio, fechaEntregaEstimada, ciudadDestino, direccionExacta);
        this.costoEnvioRapido = costoEnvioRapido;
    }
}
