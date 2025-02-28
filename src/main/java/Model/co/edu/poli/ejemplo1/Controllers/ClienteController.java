package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.ClienteDAOimp;
import Model.co.edu.poli.ejemplo1.Model.Cliente;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private ClienteDAOimp clienteDAO;

    public ClienteController() throws SQLException {
        this.clienteDAO = new ClienteDAOimp();
    }

    public void registrarCliente(String id, String nombre) {
        Cliente cliente = new Cliente(id, nombre);
        clienteDAO.registrar(cliente);
    }

    public Cliente obtenerPorId(String id) {
        return clienteDAO.obtenerPorId(id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.obtenerTodos();
    }

    public void actualizarCliente(String id, String nombre) {
        Cliente cliente = new Cliente(id, nombre);
        clienteDAO.actualizar(cliente);
    }

    public void eliminarCliente(String id) {
        clienteDAO.eliminar(id);
    }

    public void registrarClienteDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        registrarCliente(id, nombre);
        System.out.println("Cliente registrado exitosamente.");
    }

    public void mostrarTodosLosClientes() {
        for (Cliente cliente : obtenerTodosLosClientes()) {
            System.out.println(cliente);
        }
    }

    public void actualizarClienteDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente a actualizar: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre del cliente: ");
        String nombre = scanner.nextLine();
        actualizarCliente(id, nombre);
        System.out.println("Cliente actualizado exitosamente.");
    }

    public void eliminarClienteDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        String id = scanner.nextLine();
        eliminarCliente(id);
        System.out.println("Cliente eliminado exitosamente.");
    }
}
