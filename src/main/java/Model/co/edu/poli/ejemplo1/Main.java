package Model.co.edu.poli.ejemplo1;
import Model.co.edu.poli.ejemplo1.Views.ProductoView;
import Model.co.edu.poli.ejemplo1.Views.ClienteView;

import java.sql.SQLException;
import java.util.Scanner;
/*
 * Samuel Cajica Pacheco
 * Camilo Andrés Díaz Montoya 
 * Andrés David González Cuesta
 */

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Clientes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    ProductoView productoView = new ProductoView();
                    productoView.mostrarMenu();
                    break;
                case 2:
                    ClienteView clienteView = new ClienteView();
                    clienteView.mostrarMenu();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }
}
