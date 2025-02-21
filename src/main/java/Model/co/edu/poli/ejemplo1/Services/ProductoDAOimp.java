package Model.co.edu.poli.ejemplo1.Services;

import Model.co.edu.poli.ejemplo1.Model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimp implements DAO<Producto> {

    private Connection conexion;

    public ProductoDAOimp() {
        this.conexion = Conexion.obtenerInstancia().obtenerConexion();
    }

    @Override
    public void registrar(Producto producto) {
        String sql = "INSERT INTO Productos (id, tipo) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getId());
            stmt.setString(2, producto.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtenerPorId(String id) {
        String sql = "SELECT * FROM Productos WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(rs.getString("id"), rs.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(new Producto(rs.getString("id"), rs.getString("tipo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE Productos SET tipo = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getTipo());
            stmt.setString(2, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String id) {
        String sql = "DELETE FROM Productos WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
