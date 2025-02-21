// ElectricoView.java
package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.ElectricoController;
import Model.co.edu.poli.ejemplo1.Services.ElectricoDAOimp;

import java.util.Scanner;

public class ElectricoView {

    private ElectricoController electricoController;

    public ElectricoView() {
        this.electricoController = new ElectricoController(new ElectricoDAOimp());
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Registrar Producto Eléctrico");
            System.out.println("2. Mostrar Todos los Productos Eléctricos");
            System.out.println("3. Actualizar Producto Eléctrico");
            System.out.println("4. Eliminar Producto Eléctrico");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (opcion) {
                case 1:
                    electricoController.registrarElectricoDesdeConsola(null);
                    break;
                case 2:
                    electricoController.mostrarTodosLosElectricos();
                    break;
                case 3:
                    electricoController.actualizarElectricoDesdeConsola();
                    break;
                case 4:
                    electricoController.eliminarElectricoDesdeConsola();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}
