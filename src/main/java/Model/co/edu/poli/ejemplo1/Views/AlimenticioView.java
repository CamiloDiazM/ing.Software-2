// AlimenticioView.java
package Model.co.edu.poli.ejemplo1.Views;

import Model.co.edu.poli.ejemplo1.Controllers.AlimenticioController;
import Model.co.edu.poli.ejemplo1.Services.AlimenticioDAOimp;

import java.util.Scanner;

public class AlimenticioView {

    private AlimenticioController alimenticioController;

    public AlimenticioView() {
        this.alimenticioController = new AlimenticioController(new AlimenticioDAOimp());
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Registrar Producto Alimenticio");
            System.out.println("2. Mostrar Todos los Productos Alimenticios");
            System.out.println("3. Actualizar Producto Alimenticio");
            System.out.println("4. Eliminar Producto Alimenticio");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (opcion) {
                case 1:
                    alimenticioController.registrarAlimenticioDesdeConsola(null);
                    break;
                case 2:
                    alimenticioController.mostrarTodosLosAlimenticios();
                    break;
                case 3:
                    alimenticioController.actualizarAlimenticioDesdeConsola();
                    break;
                case 4:
                    alimenticioController.eliminarAlimenticioDesdeConsola();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


}
