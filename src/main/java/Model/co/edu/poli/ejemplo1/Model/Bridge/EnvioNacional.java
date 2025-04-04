package Model.co.edu.poli.ejemplo1.Model.Bridge;

public class EnvioNacional extends Envio{

    private String departamentoDestino;

    public EnvioNacional(TipoMercancia tipoMercancia, int idEnvio, String nombreDestinatario, String fechaEnvio, String fechaEntregaEstimada, String ciudadDestino, String direccionExacta, String departamentoDestino) {
        super(tipoMercancia, idEnvio, nombreDestinatario, fechaEnvio, fechaEntregaEstimada, ciudadDestino, direccionExacta);
        this.departamentoDestino = departamentoDestino;
    }
}
