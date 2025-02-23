package Model.co.edu.poli.ejemplo1;

import Model.co.edu.poli.ejemplo1.Views.ProductoView;
import Model.co.edu.poli.ejemplo1.Views.PedidoView;
import Model.co.edu.poli.ejemplo1.Views.ClienteView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Pedidos");
            System.out.println("3. Gestionar Clientes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    ProductoView productoView = new ProductoView();
                    productoView.mostrarMenu();
                    break;
                case 2:
                    PedidoView pedidoView = new PedidoView();
                    pedidoView.mostrarMenu();
                    break;
                case 3:
                    ClienteView clienteView = new ClienteView();
                    clienteView.mostrarMenu();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }
}
