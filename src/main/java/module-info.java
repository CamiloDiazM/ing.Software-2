module co.edu.poli.softwareparte2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.softwareparte2.Controller to javafx.fxml;
    opens co.edu.poli.softwareparte2.View to javafx.fxml;

    exports co.edu.poli.softwareparte2.Controller;
    exports co.edu.poli.softwareparte2.View;
}