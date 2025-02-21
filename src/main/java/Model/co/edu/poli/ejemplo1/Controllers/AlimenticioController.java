package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.DAO;
import Model.co.edu.poli.ejemplo1.Model.Alimenticio;
import java.util.List;
import java.util.Scanner;

public class AlimenticioController {

    private DAO<Alimenticio> alimenticioDAO;

    public AlimenticioController(DAO<Alimenticio> alimenticioDAO) {
        this.alimenticioDAO = alimenticioDAO;
    }

    public void registrarAlimenticio(String idProducto, String descripcion, String calorias) {
        Alimenticio alimenticio = new Alimenticio(idProducto, descripcion, calorias);
        alimenticioDAO.registrar(alimenticio);
    }

    public Alimenticio obtenerPorId(String idProducto) {
        return alimenticioDAO.obtenerPorId(idProducto);
    }

    public List<Alimenticio> obtenerTodosLosAlimenticios() {
        return alimenticioDAO.obtenerTodos();
    }

    public void actualizarAlimenticio(String idProducto, String descripcion, String calorias) {
        Alimenticio alimenticio = new Alimenticio(idProducto, descripcion, calorias);
        alimenticioDAO.actualizar(alimenticio);
    }

    public void eliminarAlimenticio(String idProducto) {
        alimenticioDAO.eliminar(idProducto);
    }

    public void registrarAlimenticioDesdeConsola(String idProducto) {
        Scanner scanner = new Scanner(System.in);
        if (idProducto == null) {
            System.out.print("Ingrese el ID del producto alimenticio: ");
            idProducto = scanner.nextLine();
        }
        System.out.print("Ingrese la descripcion del producto alimenticio: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese las calorias del producto alimenticio: ");
        String calorias = scanner.nextLine();
        registrarAlimenticio(idProducto, descripcion, calorias);
        System.out.println("Producto alimenticio registrado exitosamente.");
    }

    public void mostrarTodosLosAlimenticios() {
        for (Alimenticio alimenticio : obtenerTodosLosAlimenticios()) {
            System.out.println(alimenticio);
        }
    }

    public void actualizarAlimenticioDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto alimenticio a actualizar: ");
        String idProducto = scanner.nextLine();
        System.out.print("Ingrese la nueva descripcion del producto alimenticio: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese las nuevas calorias del producto alimenticio: ");
        String calorias = scanner.nextLine();
        actualizarAlimenticio(idProducto, descripcion, calorias);
        System.out.println("Producto alimenticio actualizado exitosamente.");
    }

    public void eliminarAlimenticioDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto alimenticio a eliminar: ");
        String idProducto = scanner.nextLine();
        eliminarAlimenticio(idProducto);
        System.out.println("Producto alimenticio eliminado exitosamente.");
    }
}
