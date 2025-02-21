package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.PedidoController;
import Model.co.edu.poli.ejemplo1.Services.PedidoDAOimp;

import java.util.Scanner;

public class PedidoView {

    private PedidoController pedidoController;

    public PedidoView() {
        this.pedidoController = new PedidoController(new PedidoDAOimp());
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Mostrar Todos los Pedidos");
            System.out.println("3. Actualizar Pedido");
            System.out.println("4. Eliminar Pedido");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consume newline

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
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
