package Model.co.edu.poli.ejemplo1.Model;

public class PaypalAdapter implements MetodoPago {
    private Paypal paypal;
    private String correo;

    public PaypalAdapter(String correo) {
        this.paypal = new Paypal();
        this.correo = correo;
    }

    @Override
    public String realizarPago(double monto) {
        return paypal.realizarPagoPaypal(monto, correo);
    }

}
