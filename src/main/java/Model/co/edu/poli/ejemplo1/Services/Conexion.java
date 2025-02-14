package Model.co.edu.poli.ejemplo1.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instancia;

    private Connection conexion;

    private static final String URL = "jdbc:mysql://localhost:3306/prueba";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Politecnico123*";

    private Conexion() {
        try {
            this.conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion obtenerInstancia() {
        if (instancia == null) {
            synchronized (Conexion.class) {
                if (instancia == null) {
                    instancia = new Conexion();
                }
            }
        }
        return instancia;
    }

    public Connection obtenerConexion() {
        return conexion;
    }
}
