module mi.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens co.edu.poli.ejercicio.Controller to javafx.fxml;
    opens co.edu.poli.ejercicio to javafx.fxml;


    exports co.edu.poli.ejercicio.Controller;
    exports co.edu.poli.ejercicio;
    exports co.edu.poli.ejercicio.View;
    opens co.edu.poli.ejercicio.View to javafx.fxml;

}