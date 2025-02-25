package Model.co.edu.poli.ejemplo1.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Conexion {
    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("config");

            String URL = bundle.getString("db.url");
            String USUARIO = bundle.getString("db.username");
            String CONTRASENA = bundle.getString("db.password");

            this.conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static Conexion obtenerInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection obtenerConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
