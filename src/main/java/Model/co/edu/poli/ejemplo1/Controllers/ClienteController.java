package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.ClienteDAO;
import Model.co.edu.poli.ejemplo1.Model.Cliente;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void registrarCliente(String id, String nombre) {
        Cliente cliente = new Cliente(id, nombre);
        clienteDAO.registrarCliente(cliente);
    }

    public Cliente obtenerClientePorId(String id) {
        return clienteDAO.obtenerClientePorId(id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.obtenerTodosLosClientes();
    }

    public void actualizarCliente(String id, String nombre) {
        Cliente cliente = new Cliente(id, nombre);
        clienteDAO.actualizarCliente(cliente);
    }

    public void eliminarCliente(String id) {
        clienteDAO.eliminarCliente(id);
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
