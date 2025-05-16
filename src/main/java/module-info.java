module mi.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    exports co.edu.poli.ejercicio.View;
    exports co.edu.poli.ejercicio.Controller;

    opens co.edu.poli.ejercicio.View to javafx.fxml;
    opens co.edu.poli.ejercicio.Controller to javafx.fxml;

}