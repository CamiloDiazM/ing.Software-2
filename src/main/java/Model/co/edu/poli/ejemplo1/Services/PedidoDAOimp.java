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
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido)) {
            stmtPedido.setString(1, pedido.getNumPedido());
            stmtPedido.setDate(2, Date.valueOf(pedido.getFecha()));
            stmtPedido.setString(3, pedido.getCliente().getId());
            stmtPedido.executeUpdate();

            for (Producto producto : pedido.getProducto()) {
                String sqlPedidoProducto = "INSERT INTO Pedido_Productos (pedido_id, producto_id) VALUES (?, ?)";
                try (PreparedStatement stmtPedidoProducto = conexion.prepareStatement(sqlPedidoProducto)) {
                    stmtPedidoProducto.setString(1, pedido.getNumPedido());
                    stmtPedidoProducto.setString(2, producto.getId());
                    stmtPedidoProducto.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido obtenerPorId(String id) {
        String sqlPedido = "SELECT * FROM Pedidos WHERE numPedido = ?";
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido)) {
            stmtPedido.setString(1, id);
            ResultSet rsPedido = stmtPedido.executeQuery();
            if (rsPedido.next()) {
                String fecha = rsPedido.getString("fecha");
                String clienteId = rsPedido.getString("cliente_id");
                Cliente cliente = new Cliente(clienteId, ""); // Assuming Cliente class has a constructor with id and name

                List<Producto> productos = obtenerProductosPorPedidoId(id);

                return new Pedido(id, cliente, fecha, productos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pedido> obtenerTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sqlPedido = "SELECT * FROM Pedidos";
        try (Statement stmtPedido = conexion.createStatement();
             ResultSet rsPedido = stmtPedido.executeQuery(sqlPedido)) {
            while (rsPedido.next()) {
                String numPedido = rsPedido.getString("numPedido");
                String fecha = rsPedido.getString("fecha");
                String clienteId = rsPedido.getString("cliente_id");
                Cliente cliente = new Cliente(clienteId, ""); // Assuming Cliente class has a constructor with id and name

                List<Producto> productos = obtenerProductosPorPedidoId(numPedido);

                pedidos.add(new Pedido(numPedido, cliente, fecha, productos));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public void actualizar(Pedido pedido) {
        String sqlPedido = "UPDATE Pedidos SET fecha = ?, cliente_id = ? WHERE numPedido = ?";
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido)) {
            stmtPedido.setDate(1, Date.valueOf(pedido.getFecha()));
            stmtPedido.setString(2, pedido.getCliente().getId());
            stmtPedido.setString(3, pedido.getNumPedido());
            stmtPedido.executeUpdate();

            // Delete existing products for the order
            String sqlDeleteProductos = "DELETE FROM Pedido_Productos WHERE pedido_id = ?";
            try (PreparedStatement stmtDeleteProductos = conexion.prepareStatement(sqlDeleteProductos)) {
                stmtDeleteProductos.setString(1, pedido.getNumPedido());
                stmtDeleteProductos.executeUpdate();
            }

            // Insert updated products for the order
            for (Producto producto : pedido.getProducto()) {
                String sqlPedidoProducto = "INSERT INTO Pedido_Productos (pedido_id, producto_id) VALUES (?, ?)";
                try (PreparedStatement stmtPedidoProducto = conexion.prepareStatement(sqlPedidoProducto)) {
                    stmtPedidoProducto.setString(1, pedido.getNumPedido());
                    stmtPedidoProducto.setString(2, producto.getId());
                    stmtPedidoProducto.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String id) {
        String sqlPedido = "DELETE FROM Pedidos WHERE numPedido = ?";
        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido)) {
            stmtPedido.setString(1, id);
            stmtPedido.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Producto> obtenerProductosPorPedidoId(String pedidoId) {
        List<Producto> productos = new ArrayList<>();
        String sqlPedidoProducto = "SELECT producto_id FROM Pedido_Productos WHERE pedido_id = ?";
        try (PreparedStatement stmtPedidoProducto = conexion.prepareStatement(sqlPedidoProducto)) {
            stmtPedidoProducto.setString(1, pedidoId);
            ResultSet rsPedidoProducto = stmtPedidoProducto.executeQuery();
            while (rsPedidoProducto.next()) {
                String productoId = rsPedidoProducto.getString("producto_id");
                Producto producto = new ProductoDAOimp().obtenerPorId(productoId);
                if (producto != null) {
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
