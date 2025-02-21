package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.AlimenticioDAOimp;
import Model.co.edu.poli.ejemplo1.Services.DAO;
import Model.co.edu.poli.ejemplo1.Model.Producto;
import Model.co.edu.poli.ejemplo1.Services.ElectricoDAOimp;

import java.util.List;
import java.util.Scanner;

public class ProductoController {

    private DAO<Producto> productoDAO;

    public ProductoController(DAO<Producto> productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void registrarProducto(String id, String tipo) {
        Producto producto = new Producto(id, tipo);
        productoDAO.registrar(producto);
    }

    public Producto obtenerPorId(String id) {
        return productoDAO.obtenerPorId(id);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.obtenerTodos();
    }

    public void actualizarProducto(String id, String tipo) {
        Producto producto = new Producto(id, tipo);
        productoDAO.actualizar(producto);
    }

    public void eliminarProducto(String id) {
        productoDAO.eliminar(id);
    }

    public void registrarProductoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el tipo del producto (Electrico/Alimenticio): ");
        String tipo = scanner.nextLine();

        registrarProducto(id, tipo);

        if (tipo.equalsIgnoreCase("Electrico")) {
            new ElectricoController(new ElectricoDAOimp()).registrarElectricoDesdeConsola(id);
        } else if (tipo.equalsIgnoreCase("Alimenticio")) {
            new AlimenticioController(new AlimenticioDAOimp()).registrarAlimenticioDesdeConsola(id);
        } else {
            System.out.println("Tipo de producto no válido.");
        }
    }

    public void mostrarTodosLosProductos() {
        for (Producto producto : obtenerTodosLosProductos()) {
            System.out.println(producto);
        }
    }

    public void actualizarProductoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto a actualizar: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nuevo tipo del producto: ");
        String tipo = scanner.nextLine();
        actualizarProducto(id, tipo);
        System.out.println("Producto actualizado exitosamente.");
    }

    public void eliminarProductoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto a eliminar: ");
        String id = scanner.nextLine();
        eliminarProducto(id);
        System.out.println("Producto eliminado exitosamente.");
    }
}
