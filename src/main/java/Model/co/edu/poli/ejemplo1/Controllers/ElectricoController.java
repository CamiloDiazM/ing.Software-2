package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.DAO;
import Model.co.edu.poli.ejemplo1.Model.Electrico;
import java.util.List;
import java.util.Scanner;

public class ElectricoController {

    private DAO<Electrico> electricoDAO;

    public ElectricoController(DAO<Electrico> electricoDAO) {
        this.electricoDAO = electricoDAO;
    }

    public void registrarElectrico(String idProducto, String descripcion, String voltaje) {
        Electrico electrico = new Electrico(idProducto, descripcion, voltaje);
        electricoDAO.registrar(electrico);
    }

    public Electrico obtenerPorId(String idProducto) {
        return electricoDAO.obtenerPorId(idProducto);
    }

    public List<Electrico> obtenerTodosLosElectricos() {
        return electricoDAO.obtenerTodos();
    }

    public void actualizarElectrico(String idProducto, String descripcion, String voltaje) {
        Electrico electrico = new Electrico(idProducto, descripcion, voltaje);
        electricoDAO.actualizar(electrico);
    }

    public void eliminarElectrico(String idProducto) {
        electricoDAO.eliminar(idProducto);
    }

    public void registrarElectricoDesdeConsola(String idProducto) {
        Scanner scanner = new Scanner(System.in);
        if (idProducto == null) {
            System.out.print("Ingrese el ID del producto electrico: ");
            idProducto = scanner.nextLine();
        }
        System.out.print("Ingrese la descripcion del producto electrico: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el voltaje del producto electrico: ");
        String voltaje = scanner.nextLine();
        registrarElectrico(idProducto, descripcion, voltaje);
        System.out.println("Producto electrico registrado exitosamente.");
    }

    public void mostrarTodosLosElectricos() {
        for (Electrico electrico : obtenerTodosLosElectricos()) {
            System.out.println(electrico);
        }
    }

    public void actualizarElectricoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto electrico a actualizar: ");
        String idProducto = scanner.nextLine();
        System.out.print("Ingrese la nueva descripcion del producto electrico: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el nuevo voltaje del producto electrico: ");
        String voltaje = scanner.nextLine();
        actualizarElectrico(idProducto, descripcion, voltaje);
        System.out.println("Producto electrico actualizado exitosamente.");
    }

    public void eliminarElectricoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto electrico a eliminar: ");
        String idProducto = scanner.nextLine();
        eliminarElectrico(idProducto);
        System.out.println("Producto electrico eliminado exitosamente.");
    }
}
