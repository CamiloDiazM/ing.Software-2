package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Model.*;
import Model.co.edu.poli.ejemplo1.Services.PedidoDAOimp;
import Model.co.edu.poli.ejemplo1.Services.ProductoDAOimp;
import Model.co.edu.poli.ejemplo1.Services.ClienteDAOimp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoController {

    private PedidoDAOimp pedidoDAO;
    private ProductoDAOimp productoDAO;
    private ClienteDAOimp clienteDAO;

    public PedidoController() {
        this.pedidoDAO = new PedidoDAOimp();
        this.productoDAO = new ProductoDAOimp();
        this.clienteDAO = new ClienteDAOimp();
    }

    public void registrarPedidoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese numero de pedido: ");
        String numPedido = scanner.nextLine();
        System.out.print("Ingrese fecha del pedido (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese ID del cliente: ");
        String clienteId = scanner.nextLine();
        Cliente cliente = clienteDAO.obtenerPorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Get products by ID from the user
        List<Producto> productos = obtenerProductosPorIdDesdeConsola();

        Pedido pedido = new Pedido(numPedido, cliente, fecha, productos);
        pedidoDAO.registrar(pedido);
    }

    private List<Producto> obtenerProductosPorIdDesdeConsola() {
        List<Producto> productos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String continuar;

        do {
            System.out.print("Ingrese ID del producto: ");
            String id = scanner.nextLine();
            Producto producto = productoDAO.obtenerPorId(id);
            if (producto != null) {
                productos.add(producto);
            } else {
                System.out.println("Producto no encontrado.");
            }

            System.out.print("¿Desea agregar otro producto? (si/no): ");
            continuar = scanner.nextLine();
        } while ("si".equalsIgnoreCase(continuar));

        return productos;
    }

    public void mostrarTodosLosPedidos() {
        List<Pedido> pedidos = pedidoDAO.obtenerTodos();
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    public void actualizarPedidoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese numero de pedido a actualizar: ");
        String numPedido = scanner.nextLine();
        Pedido pedido = pedidoDAO.obtenerPorId(numPedido);
        if (pedido != null) {
            System.out.print("Ingrese nueva fecha del pedido (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();
            pedido.setFecha(fecha);

            // Get products by ID from the user
            List<Producto> productos = obtenerProductosPorIdDesdeConsola();
            pedido.setProducto(productos);

            pedidoDAO.actualizar(pedido);
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }

    public void eliminarPedidoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese numero de pedido a eliminar: ");
        String numPedido = scanner.nextLine();
        pedidoDAO.eliminar(numPedido);
    }
}