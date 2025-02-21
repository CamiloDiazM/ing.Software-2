package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.ClienteDAOimp;
import Model.co.edu.poli.ejemplo1.Services.DAO;
import Model.co.edu.poli.ejemplo1.Model.Pedido;
import Model.co.edu.poli.ejemplo1.Model.Cliente;
import Model.co.edu.poli.ejemplo1.Model.Producto;
import Model.co.edu.poli.ejemplo1.Services.ProductoDAOimp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoController {

    private DAO<Pedido> pedidoDAO;

    public PedidoController(DAO<Pedido> pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public void registrarPedido(String numPedido, Cliente cliente, String fecha, List<Producto> productos) {
        Pedido pedido = new Pedido(numPedido, cliente, fecha, productos);
        pedidoDAO.registrar(pedido);
    }

    public Pedido obtenerPorId(String numPedido) {
        return pedidoDAO.obtenerPorId(numPedido);
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoDAO.obtenerTodos();
    }

    public void actualizarPedido(String numPedido, Cliente cliente, String fecha, List<Producto> productos) {
        Pedido pedido = new Pedido(numPedido, cliente, fecha, productos);
        pedidoDAO.actualizar(pedido);
    }

    public void eliminarPedido(String numPedido) {
        pedidoDAO.eliminar(numPedido);
    }

    public void registrarPedidoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del pedido: ");
        String numPedido = scanner.nextLine();
        System.out.print("Ingrese la fecha del pedido (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el ID del cliente: ");
        String clienteId = scanner.nextLine();
        Cliente cliente = new ClienteController(new ClienteDAOimp()).obtenerPorId(clienteId);

        List<Producto> productos = new ArrayList<>();
        while (true) {
            System.out.print("Ingrese el ID del producto (o 'fin' para terminar): ");
            String productoId = scanner.nextLine();
            if (productoId.equalsIgnoreCase("fin")) {
                break;
            }
            Producto producto = new ProductoController(new ProductoDAOimp()).obtenerPorId(productoId);
            productos.add(producto);
        }

        registrarPedido(numPedido, cliente, fecha, productos);
        System.out.println("Pedido registrado exitosamente.");
    }

    public void mostrarTodosLosPedidos() {
        for (Pedido pedido : obtenerTodosLosPedidos()) {
            System.out.println(pedido);
        }
    }

    public void actualizarPedidoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del pedido a actualizar: ");
        String numPedido = scanner.nextLine();
        System.out.print("Ingrese la nueva fecha del pedido (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el nuevo ID del cliente: ");
        String clienteId = scanner.nextLine();
        Cliente cliente = new ClienteController(new ClienteDAOimp()).obtenerPorId(clienteId);

        List<Producto> productos = new ArrayList<>();
        while (true) {
            System.out.print("Ingrese el ID del producto (o 'fin' para terminar): ");
            String productoId = scanner.nextLine();
            if (productoId.equalsIgnoreCase("fin")) {
                break;
            }
            Producto producto = new ProductoController(new ProductoDAOimp()).obtenerPorId(productoId);
            productos.add(producto);
        }

        actualizarPedido(numPedido, cliente, fecha, productos);
        System.out.println("Pedido actualizado exitosamente.");
    }

    public void eliminarPedidoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del pedido a eliminar: ");
        String numPedido = scanner.nextLine();
        eliminarPedido(numPedido);
        System.out.println("Pedido eliminado exitosamente.");
    }
}
