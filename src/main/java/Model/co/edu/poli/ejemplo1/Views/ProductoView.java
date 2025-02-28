package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.ProductoController;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductoView {

    private ProductoController productoController;

    public ProductoView() {
        try {
            this.productoController = new ProductoController();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu de Productos:");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Mostrar Todos los Productos");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Consultar Productos por Tipo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    productoController.registrarProductoDesdeConsola();
                    break;
                case 2:
                    productoController.mostrarTodosLosProductos();
                    break;
                case 3:
                    productoController.actualizarProductoDesdeConsola();
                    break;
                case 4:
                    productoController.eliminarProductoDesdeConsola();
                    break;
                case 5:
                    productoController.mostrarProductosPorTipo();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }
}
