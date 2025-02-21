package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.ProductoController;
import Model.co.edu.poli.ejemplo1.Services.ProductoDAOimp;

import java.util.Scanner;

public class ProductoView {

    private ProductoController productoController;

    public ProductoView() {
        this.productoController = new ProductoController(new ProductoDAOimp());
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Registrar Producto");
            System.out.println("2. Mostrar Todos los Productos");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consume newline

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
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


}
