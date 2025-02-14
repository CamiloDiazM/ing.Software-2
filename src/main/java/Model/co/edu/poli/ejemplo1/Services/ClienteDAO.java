package Model.co.edu.poli.ejemplo1.Services;

import Model.co.edu.poli.ejemplo1.Model.Cliente;
import java.util.List;

public interface ClienteDAO {
    void registrarCliente(Cliente cliente);
    Cliente obtenerClientePorId(String id);
    List<Cliente> obtenerTodosLosClientes();
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(String id);
}
