package Model.co.edu.poli.ejemplo1.Model;

public class NequiAdapter implements MetodoPago {
    private Nequi nequi;
    private String numero;

    public NequiAdapter(String numero) {
        this.nequi = new Nequi();
        this.numero = numero;
    }

    @Override
    public String realizarPago(double monto) {
        return nequi.realizarPagoNequi(monto, numero);
    }
}
