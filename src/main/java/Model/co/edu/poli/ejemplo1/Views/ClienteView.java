package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.ClienteController;

import java.sql.SQLException;
import java.util.Scanner;

public class ClienteView {

    private ClienteController clienteController;

    public ClienteView(){
        try {
            this.clienteController = new ClienteController();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Menu de Clientes:");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Mostrar Todos los Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    clienteController.registrarClienteDesdeConsola();
                    break;
                case 2:
                    clienteController.mostrarTodosLosClientes();
                    break;
                case 3:
                    clienteController.actualizarClienteDesdeConsola();
                    break;
                case 4:
                    clienteController.eliminarClienteDesdeConsola();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

}