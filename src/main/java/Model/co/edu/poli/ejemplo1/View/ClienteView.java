package Model.co.edu.poli.ejemplo1.View;

import Model.co.edu.poli.ejemplo1.Controllers.ClienteController;
import Model.co.edu.poli.ejemplo1.Model.Cliente;
import java.util.Scanner;

public class ClienteView {

    private ClienteController clienteController;

    public ClienteView() {
        this.clienteController = new ClienteController();
    }

    public void registrarClienteDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        clienteController.registrarCliente(id, nombre);
        System.out.println("Cliente registrado exitosamente.");
    }

    public void mostrarTodosLosClientes() {
        for (Cliente cliente : clienteController.obtenerTodosLosClientes()) {
            System.out.println(cliente);
        }
    }

    public void actualizarClienteDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente a actualizar: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre del cliente: ");
        String nombre = scanner.nextLine();
        clienteController.actualizarCliente(id, nombre);
        System.out.println("Cliente actualizado exitosamente.");
    }

    public void eliminarClienteDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        String id = scanner.nextLine();
        clienteController.eliminarCliente(id);
        System.out.println("Cliente eliminado exitosamente.");
    }

    public static void main(String[] args) {
        ClienteView vista = new ClienteView();
        vista.registrarClienteDesdeConsola();
        vista.mostrarTodosLosClientes();
        vista.actualizarClienteDesdeConsola();
        vista.eliminarClienteDesdeConsola();
    }
}
