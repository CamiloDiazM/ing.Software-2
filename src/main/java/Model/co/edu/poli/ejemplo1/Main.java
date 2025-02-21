package Model.co.edu.poli.ejemplo1;

import Model.co.edu.poli.ejemplo1.Views.ClienteView;
import Model.co.edu.poli.ejemplo1.Views.PedidoView;
import Model.co.edu.poli.ejemplo1.Views.ProductoView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Principal:");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Pedidos");
            System.out.println("3. Gestionar Productos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (opcion) {
                case 1:
                    new ClienteView().mostrarMenu();
                    break;
                case 2:
                    new PedidoView().mostrarMenu();
                    break;
                case 3:
                    new ProductoView().mostrarMenu();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
