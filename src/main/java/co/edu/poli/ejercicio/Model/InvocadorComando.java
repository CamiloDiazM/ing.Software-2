package co.edu.poli.ejercicio.Model;

public class InvocadorComando {
    private Comando comando;

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public void ejecutarComando() {
        if (comando != null) {
            comando.ejecutar();
        }
    }
}
