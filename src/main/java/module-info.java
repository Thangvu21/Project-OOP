module com.example.demodb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demodb to javafx.fxml;
    exports com.example.demodb;
}