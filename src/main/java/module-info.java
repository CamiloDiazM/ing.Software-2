module mi.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens Model.co.edu.poli.ejemplo1 to javafx.fxml;
    opens Model.co.edu.poli.ejemplo1.Controllers to javafx.fxml;

    exports Model.co.edu.poli.ejemplo1;
    exports Model.co.edu.poli.ejemplo1.Controllers;

}