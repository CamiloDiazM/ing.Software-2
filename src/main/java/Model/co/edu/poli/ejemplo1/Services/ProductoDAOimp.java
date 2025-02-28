package Model.co.edu.poli.ejemplo1.Services;

import Model.co.edu.poli.ejemplo1.Model.Producto;
import Model.co.edu.poli.ejemplo1.Model.Electrico;
import Model.co.edu.poli.ejemplo1.Model.Alimenticio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimp implements ProductoDAO {

    private Connection conexion;

    public ProductoDAOimp() throws SQLException {
        this.conexion = Conexion.obtenerInstancia().obtenerConexion();
    }

    @Override
    public void registrar(Producto producto) {
        String sqlProducto = "INSERT INTO Productos (id, tipo, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement stmtProducto = conexion.prepareStatement(sqlProducto)) {
            // Check if descripcion is null or empty before executing the update
            if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
                throw new SQLException("Descripcion no puede estar vacia.");
            }

            stmtProducto.setString(1, producto.getId());
            stmtProducto.setString(2, producto.getTipo());
            stmtProducto.setString(3, producto.getDescripcion().trim());

            stmtProducto.executeUpdate();

            if (producto instanceof Electrico) {
                String sqlElectrico = "INSERT INTO Electricos (id_producto, voltaje) VALUES (?, ?)";
                try (PreparedStatement stmtElectrico = conexion.prepareStatement(sqlElectrico)) {
                    stmtElectrico.setString(1, producto.getId());
                    stmtElectrico.setString(2, ((Electrico) producto).getVoltaje());
                    stmtElectrico.executeUpdate();
                }
            } else if (producto instanceof Alimenticio) {
                String sqlAlimenticio = "INSERT INTO Alimenticios (id_producto, calorias) VALUES (?, ?)";
                try (PreparedStatement stmtAlimenticio = conexion.prepareStatement(sqlAlimenticio)) {
                    stmtAlimenticio.setString(1, producto.getId());
                    stmtAlimenticio.setString(2, ((Alimenticio) producto).getCalorias());
                    stmtAlimenticio.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtenerPorId(String id) {
        String sqlProducto = "SELECT * FROM Productos WHERE id = ?";
        try (PreparedStatement stmtProducto = conexion.prepareStatement(sqlProducto)) {
            stmtProducto.setString(1, id);
            ResultSet rsProducto = stmtProducto.executeQuery();
            if (rsProducto.next()) {
                String tipo = rsProducto.getString("tipo");
                String descripcion = rsProducto.getString("descripcion");

                if ("Electrico".equals(tipo)) {
                    String sqlElectrico = "SELECT * FROM Electricos WHERE id_producto = ?";
                    try (PreparedStatement stmtElectrico = conexion.prepareStatement(sqlElectrico)) {
                        stmtElectrico.setString(1, id);
                        ResultSet rsElectrico = stmtElectrico.executeQuery();
                        if (rsElectrico.next()) {
                            String voltaje = rsElectrico.getString("voltaje");
                            return new Electrico(id, tipo, descripcion, voltaje);
                        }
                    }
                } else if ("Alimenticio".equals(tipo)) {
                    String sqlAlimenticio = "SELECT * FROM Alimenticios WHERE id_producto = ?";
                    try (PreparedStatement stmtAlimenticio = conexion.prepareStatement(sqlAlimenticio)) {
                        stmtAlimenticio.setString(1, id);
                        ResultSet rsAlimenticio = stmtAlimenticio.executeQuery();
                        if (rsAlimenticio.next()) {
                            String calorias = rsAlimenticio.getString("calorias");
                            return new Alimenticio(id, tipo, descripcion, calorias);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sqlProducto = "SELECT * FROM Productos";
        try (Statement stmtProducto = conexion.createStatement();
             ResultSet rsProducto = stmtProducto.executeQuery(sqlProducto)) {
            while (rsProducto.next()) {
                String id = rsProducto.getString("id");
                String tipo = rsProducto.getString("tipo");
                String descripcion = rsProducto.getString("descripcion");

                if ("Electrico".equals(tipo)) {
                    String sqlElectrico = "SELECT * FROM Electricos WHERE id_producto = ?";
                    try (PreparedStatement stmtElectrico = conexion.prepareStatement(sqlElectrico)) {
                        stmtElectrico.setString(1, id);
                        ResultSet rsElectrico = stmtElectrico.executeQuery();
                        if (rsElectrico.next()) {
                            String voltaje = rsElectrico.getString("voltaje");
                            productos.add(new Electrico(id, tipo, descripcion, voltaje));
                        }
                    }
                } else if ("Alimenticio".equals(tipo)) {
                    String sqlAlimenticio = "SELECT * FROM Alimenticios WHERE id_producto = ?";
                    try (PreparedStatement stmtAlimenticio = conexion.prepareStatement(sqlAlimenticio)) {
                        stmtAlimenticio.setString(1, id);
                        ResultSet rsAlimenticio = stmtAlimenticio.executeQuery();
                        if (rsAlimenticio.next()) {
                            String calorias = rsAlimenticio.getString("calorias");
                            productos.add(new Alimenticio(id, tipo, descripcion, calorias));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sqlProducto = "UPDATE Productos SET descripcion = ? WHERE id = ?";
        try (PreparedStatement stmtProducto = conexion.prepareStatement(sqlProducto)) {
            stmtProducto.setString(1, producto.getDescripcion());
            stmtProducto.setString(2, producto.getId());
            stmtProducto.executeUpdate();

            if (producto instanceof Electrico) {
                String sqlElectrico = "UPDATE Electricos SET voltaje = ? WHERE id_producto = ?";
                try (PreparedStatement stmtElectrico = conexion.prepareStatement(sqlElectrico)) {
                    stmtElectrico.setString(1, ((Electrico) producto).getVoltaje());
                    stmtElectrico.setString(2, producto.getId());
                    stmtElectrico.executeUpdate();
                }
            } else if (producto instanceof Alimenticio) {
                String sqlAlimenticio = "UPDATE Alimenticios SET calorias = ? WHERE id_producto = ?";
                try (PreparedStatement stmtAlimenticio = conexion.prepareStatement(sqlAlimenticio)) {
                    stmtAlimenticio.setString(1, ((Alimenticio) producto).getCalorias());
                    stmtAlimenticio.setString(2, producto.getId());
                    stmtAlimenticio.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String id) {
        String sqlProducto = "DELETE FROM Productos WHERE id = ?";
        try (PreparedStatement stmtProducto = conexion.prepareStatement(sqlProducto)) {
            stmtProducto.setString(1, id);
            stmtProducto.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> obtenerPorTipo(String tipo) {
        List<Producto> productos = new ArrayList<>();
        String sqlProducto = "SELECT * FROM Productos WHERE tipo = ?";
        try (PreparedStatement stmtProducto = conexion.prepareStatement(sqlProducto)) {
            stmtProducto.setString(1, tipo);
            ResultSet rsProducto = stmtProducto.executeQuery();
            while (rsProducto.next()) {
                String id = rsProducto.getString("id");
                String descripcion = rsProducto.getString("descripcion");

                if ("Electrico".equals(tipo)) {
                    String sqlElectrico = "SELECT * FROM Electricos WHERE id_producto = ?";
                    try (PreparedStatement stmtElectrico = conexion.prepareStatement(sqlElectrico)) {
                        stmtElectrico.setString(1, id);
                        ResultSet rsElectrico = stmtElectrico.executeQuery();
                        if (rsElectrico.next()) {
                            String voltaje = rsElectrico.getString("voltaje");
                            productos.add(new Electrico(id, tipo, descripcion, voltaje));
                        }
                    }
                } else if ("Alimenticio".equals(tipo)) {
                    String sqlAlimenticio = "SELECT * FROM Alimenticios WHERE id_producto = ?";
                    try (PreparedStatement stmtAlimenticio = conexion.prepareStatement(sqlAlimenticio)) {
                        stmtAlimenticio.setString(1, id);
                        ResultSet rsAlimenticio = stmtAlimenticio.executeQuery();
                        if (rsAlimenticio.next()) {
                            String calorias = rsAlimenticio.getString("calorias");
                            productos.add(new Alimenticio(id, tipo, descripcion, calorias));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
