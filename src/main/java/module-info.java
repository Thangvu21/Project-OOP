module com.example.demodb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires freetts;
    requires voicerss.tts;
    requires java.desktop;

    opens Base to javafx.base;
    opens com.example.demoDB to javafx.fxml;
    exports com.example.demoDB;
    exports Base;
}
