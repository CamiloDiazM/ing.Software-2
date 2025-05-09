package co.edu.poli.ejercicio.Model;

public class ReducirStockComando implements Comando {
    private GestorPedido gestor;

    public ReducirStockComando(GestorPedido gestor) {
        this.gestor = gestor;
    }

    @Override
    public void ejecutar() {
        gestor.reducirStock();
        System.out.println("Stock reducido desde comando.");
    }
}
