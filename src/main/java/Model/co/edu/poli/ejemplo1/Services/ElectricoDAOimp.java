package Model.co.edu.poli.ejemplo1.Services;

import Model.co.edu.poli.ejemplo1.Model.Electrico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ElectricoDAOimp implements DAO<Electrico> {

    private Connection conexion;

    public ElectricoDAOimp() {
        this.conexion = Conexion.obtenerInstancia().obtenerConexion();
    }

    @Override
    public void registrar(Electrico electrico) {
        String sql = "INSERT INTO Electricos (id_producto, descripcion, voltaje) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, electrico.getIdProducto());
            stmt.setString(2, electrico.getDescripcion());
            stmt.setString(3, electrico.getVoltaje());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Electrico obtenerPorId(String id) {
        String sql = "SELECT * FROM Electricos WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Electrico(rs.getString("id_producto"), rs.getString("descripcion"), rs.getString("voltaje"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Electrico> obtenerTodos() {
        List<Electrico> electricos = new ArrayList<>();
        String sql = "SELECT * FROM Electricos";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                electricos.add(new Electrico(rs.getString("id_producto"), rs.getString("descripcion"), rs.getString("voltaje")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return electricos;
    }

    @Override
    public void actualizar(Electrico electrico) {
        String sql = "UPDATE Electricos SET descripcion = ?, voltaje = ? WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, electrico.getDescripcion());
            stmt.setString(2, electrico.getVoltaje());
            stmt.setString(3, electrico.getIdProducto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String id) {
        String sql = "DELETE FROM Electricos WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
