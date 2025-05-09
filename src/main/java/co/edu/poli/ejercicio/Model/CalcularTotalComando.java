package co.edu.poli.ejercicio.Model;

public class CalcularTotalComando implements Comando {
    private GestorPedido gestor;

    public CalcularTotalComando(GestorPedido gestor) {
        this.gestor = gestor;
    }

    @Override
    public void ejecutar() {
        double total = gestor.calcularTotal();
        System.out.println("Total calculado desde comando: $" + total);
    }
}
