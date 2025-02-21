package Model.co.edu.poli.ejemplo1.Services;

import Model.co.edu.poli.ejemplo1.Model.Alimenticio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimenticioDAOimp implements DAO<Alimenticio> {

    private Connection conexion;

    public AlimenticioDAOimp() {
        this.conexion = Conexion.obtenerInstancia().obtenerConexion();
    }

    @Override
    public void registrar(Alimenticio alimenticio) {
        String sql = "INSERT INTO Alimenticios (id_producto, descripcion, calorias) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, alimenticio.getIdProducto());
            stmt.setString(2, alimenticio.getDescripcion());
            stmt.setString(3, alimenticio.getCalorias());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Alimenticio obtenerPorId(String id) {
        String sql = "SELECT * FROM Alimenticios WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Alimenticio(rs.getString("id_producto"), rs.getString("descripcion"), rs.getString("calorias"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Alimenticio> obtenerTodos() {
        List<Alimenticio> alimenticios = new ArrayList<>();
        String sql = "SELECT * FROM Alimenticios";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alimenticios.add(new Alimenticio(rs.getString("id_producto"), rs.getString("descripcion"), rs.getString("calorias")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alimenticios;
    }

    @Override
    public void actualizar(Alimenticio alimenticio) {
        String sql = "UPDATE Alimenticios SET descripcion = ?, calorias = ? WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, alimenticio.getDescripcion());
            stmt.setString(2, alimenticio.getCalorias());
            stmt.setString(3, alimenticio.getIdProducto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String id) {
        String sql = "DELETE FROM Alimenticios WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}