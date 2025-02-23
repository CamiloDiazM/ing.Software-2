package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.ProductoController;

import java.util.Scanner;

public class ProductoView {

    private ProductoController productoController;

    public ProductoView() {
        this.productoController = new ProductoController();
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
            System.out.println("5. Salir");
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
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    public static void main(String[] args) {
        ProductoView view = new ProductoView();
        view.mostrarMenu();
    }
}
