package Model.co.edu.poli.ejemplo1.Services;

import Model.co.edu.poli.ejemplo1.Model.Pedido;
import Model.co.edu.poli.ejemplo1.Model.Cliente;
import Model.co.edu.poli.ejemplo1.Model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOimp implements DAO<Pedido> {

    private Connection conexion;

    public PedidoDAOimp() {
        this.conexion = Conexion.obtenerInstancia().obtenerConexion();
    }

    @Override
    public void registrar(Pedido pedido) {
        String sqlPedido = "INSERT INTO Pedidos (numPedido, fecha, cliente_id) VALUES (?, ?, ?)";
        String sqlPedidoProducto = "INSERT INTO Pedido_Productos (pedido_id, producto_id) VALUES (?, ?)";
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido);
             PreparedStatement stmtPedidoProducto = conexion.prepareStatement(sqlPedidoProducto)) {
            stmtPedido.setString(1, pedido.getNumPedido());
            stmtPedido.setDate(2, Date.valueOf(pedido.getFecha()));
            stmtPedido.setString(3, pedido.getCliente().getId());
            stmtPedido.executeUpdate();

            for (Producto producto : pedido.getProducto()) {
                stmtPedidoProducto.setString(1, pedido.getNumPedido());
                stmtPedidoProducto.setString(2, producto.getId());
                stmtPedidoProducto.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido obtenerPorId(String numPedido) {
        String sqlPedido = "SELECT * FROM Pedidos WHERE numPedido = ?";
        String sqlProductos = "SELECT p.* FROM Productos p JOIN Pedido_Productos pp ON p.id = pp.producto_id WHERE pp.pedido_id = ?";
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido);
             PreparedStatement stmtProductos = conexion.prepareStatement(sqlProductos)) {
            stmtPedido.setString(1, numPedido);
            ResultSet rsPedido = stmtPedido.executeQuery();
            if (rsPedido.next()) {
                Cliente cliente = new Cliente(rsPedido.getString("cliente_id"), null); // Fetch cliente details separately if needed
                stmtProductos.setString(1, numPedido);
                ResultSet rsProductos = stmtProductos.executeQuery();
                List<Producto> productos = new ArrayList<>();
                while (rsProductos.next()) {
                    productos.add(new Producto(rsProductos.getString("id"), rsProductos.getString("tipo")));
                }
                return new Pedido(rsPedido.getString("numPedido"), cliente, rsPedido.getString("fecha"), productos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pedido> obtenerTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sqlPedidos = "SELECT * FROM Pedidos";
        String sqlProductos = "SELECT p.* FROM Productos p JOIN Pedido_Productos pp ON p.id = pp.producto_id WHERE pp.pedido_id = ?";
        try (Statement stmtPedidos = conexion.createStatement();
             PreparedStatement stmtProductos = conexion.prepareStatement(sqlProductos);
             ResultSet rsPedidos = stmtPedidos.executeQuery(sqlPedidos)) {
            while (rsPedidos.next()) {
                Cliente cliente = new Cliente(rsPedidos.getString("cliente_id"), null); // Fetch cliente details separately if needed
                stmtProductos.setString(1, rsPedidos.getString("numPedido"));
                ResultSet rsProductos = stmtProductos.executeQuery();
                List<Producto> productos = new ArrayList<>();
                while (rsProductos.next()) {
                    productos.add(new Producto(rsProductos.getString("id"), rsProductos.getString("tipo")));
                }
                pedidos.add(new Pedido(rsPedidos.getString("numPedido"), cliente, rsPedidos.getString("fecha"), productos));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public void actualizar(Pedido pedido) {
        String sqlPedido = "UPDATE Pedidos SET fecha = ?, cliente_id = ? WHERE numPedido = ?";
        String sqlDeleteProductos = "DELETE FROM Pedido_Productos WHERE pedido_id = ?";
        String sqlInsertProducto = "INSERT INTO Pedido_Productos (pedido_id, producto_id) VALUES (?, ?)";
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido);
             PreparedStatement stmtDeleteProductos = conexion.prepareStatement(sqlDeleteProductos);
             PreparedStatement stmtInsertProducto = conexion.prepareStatement(sqlInsertProducto)) {
            stmtPedido.setDate(1, Date.valueOf(pedido.getFecha()));
            stmtPedido.setString(2, pedido.getCliente().getId());
            stmtPedido.setString(3, pedido.getNumPedido());
            stmtPedido.executeUpdate();

            stmtDeleteProductos.setString(1, pedido.getNumPedido());
            stmtDeleteProductos.executeUpdate();

            for (Producto producto : pedido.getProducto()) {
                stmtInsertProducto.setString(1, pedido.getNumPedido());
                stmtInsertProducto.setString(2, producto.getId());
                stmtInsertProducto.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String numPedido) {
        String sqlPedido = "DELETE FROM Pedidos WHERE numPedido = ?";
        String sqlProductos = "DELETE FROM Pedido_Productos WHERE pedido_id = ?";
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido);
             PreparedStatement stmtProductos = conexion.prepareStatement(sqlProductos)) {
            stmtPedido.setString(1, numPedido);
            stmtPedido.executeUpdate();

            stmtProductos.setString(1, numPedido);
            stmtProductos.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
