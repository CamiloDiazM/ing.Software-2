package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.PedidoController;

import java.util.Scanner;

public class PedidoView {

    private PedidoController pedidoController;

    public PedidoView() {
        this.pedidoController = new PedidoController();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu de Pedidos:");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Mostrar Todos los Pedidos");
            System.out.println("3. Actualizar Pedido");
            System.out.println("4. Eliminar Pedido");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    pedidoController.registrarPedidoDesdeConsola();
                    break;
                case 2:
                    pedidoController.mostrarTodosLosPedidos();
                    break;
                case 3:
                    pedidoController.actualizarPedidoDesdeConsola();
                    break;
                case 4:
                    pedidoController.eliminarPedidoDesdeConsola();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    public static void main(String[] args) {
        PedidoView view = new PedidoView();
        view.mostrarMenu();
    }
}
