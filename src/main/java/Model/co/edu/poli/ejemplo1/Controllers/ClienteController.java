package Model.co.edu.poli.ejemplo1.Controllers;

import Model.co.edu.poli.ejemplo1.Services.ClienteDAO;
import Model.co.edu.poli.ejemplo1.Services.ClienteDAOimp;
import Model.co.edu.poli.ejemplo1.Model.Cliente;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAOimp();
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
}
