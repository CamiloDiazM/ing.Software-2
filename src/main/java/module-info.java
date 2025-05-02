module co.edu.poli.observermemento {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.poli.observermemento.view to javafx.fxml;
    opens co.edu.poli.observermemento.Controller to javafx.fxml;

    exports co.edu.poli.observermemento;
}