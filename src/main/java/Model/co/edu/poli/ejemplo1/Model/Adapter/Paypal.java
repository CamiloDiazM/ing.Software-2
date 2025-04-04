package Model.co.edu.poli.ejemplo1.Model.Adapter;

public class Paypal {
    public String realizarPagoPaypal(double monto, String correo) {
        return "Pago realizado con Paypal por un monto de " + monto;
    }
}
