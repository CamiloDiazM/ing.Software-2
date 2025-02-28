package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Model.AlimenticioFactory;
import Model.co.edu.poli.ejemplo1.Model.ElectricoFactory;
import Model.co.edu.poli.ejemplo1.Model.ProductoFactory;
import Model.co.edu.poli.ejemplo1.Model.Producto;
import Model.co.edu.poli.ejemplo1.Model.Electrico;
import Model.co.edu.poli.ejemplo1.Model.Alimenticio;
import Model.co.edu.poli.ejemplo1.Services.ProductoDAO;
import Model.co.edu.poli.ejemplo1.Services.ProductoDAOimp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductoController {

    private ProductoDAO productoDAO;

    public ProductoController() throws SQLException {
        this.productoDAO = new ProductoDAOimp();
    }

    public void registrarProductoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese tipo del producto (Electrico/Alimenticio): ");
        String tipo = scanner.nextLine();
        System.out.print("Ingrese descripcion del producto: ");
        String descripcion = scanner.nextLine();

        ProductoFactory factory;
        Producto producto = null;

        if ("Electrico".equalsIgnoreCase(tipo)) {
            System.out.print("Ingrese voltaje del producto: ");
            String voltaje = scanner.nextLine();
            factory = new ElectricoFactory();
            producto = factory.crearProducto(id, tipo, descripcion, voltaje);
        } else if ("Alimenticio".equalsIgnoreCase(tipo)) {
            System.out.print("Ingrese calorias del producto: ");
            String calorias = scanner.nextLine();
            factory = new AlimenticioFactory();
            producto = factory.crearProducto(id, tipo, descripcion, calorias);
        } else {
            System.out.println("Tipo de producto no valido.");
        }

        if (producto != null) {
            productoDAO.registrar(producto);
        }
    }

    public void mostrarTodosLosProductos() {
        List<Producto> productos = productoDAO.obtenerTodos();
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public void actualizarProductoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto a actualizar: ");
        String id = scanner.nextLine();
        Producto producto = productoDAO.obtenerPorId(id);
        if (producto != null) {
            System.out.print("Ingrese nueva descripcion del producto: ");
            String descripcion = scanner.nextLine();
            producto.setDescripcion(descripcion);

            if (producto instanceof Electrico) {
                System.out.print("Ingrese nuevo voltaje del producto: ");
                String voltaje = scanner.nextLine();
                ((Electrico) producto).setVoltaje(voltaje);
            } else if (producto instanceof Alimenticio) {
                System.out.print("Ingrese nuevas calorias del producto: ");
                String calorias = scanner.nextLine();
                ((Alimenticio) producto).setCalorias(calorias);
            }

            productoDAO.actualizar(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void eliminarProductoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese ID del producto a eliminar: ");
        String id = scanner.nextLine();
        productoDAO.eliminar(id);
    }

    public void mostrarProductosPorTipo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tipo de producto a consultar: ");
        String tipo = scanner.nextLine();
        List<Producto> productos = productoDAO.obtenerPorTipo(tipo);
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
}
