package Model.co.edu.poli.ejemplo1.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Conexion {
    private static Conexion instancia;
    private Connection conexion;

    private Conexion() throws SQLException {
        ResourceBundle bundle = ResourceBundle.getBundle("config");

        String URL = bundle.getString("db.url");
        String USUARIO = bundle.getString("db.username");
        String CONTRASENA = bundle.getString("db.password");

        this.conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public static Conexion obtenerInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection obtenerConexion() {
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
