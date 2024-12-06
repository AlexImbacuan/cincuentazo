module com.example.cincuentazo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.cincuentazo to javafx.fxml;
    exports com.example.cincuentazo;
    exports com.example.cincuentazo.Controller;
    opens com.example.cincuentazo.Controller to javafx.fxml;
}