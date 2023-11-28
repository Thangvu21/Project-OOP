package com.example.demoDB;

import Base.DictionaryManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController {
    @FXML
    private TextArea textArea1;

    @FXML
    private TextField textArea2;

    @FXML
    private Button translate;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void translate() {
        if (!textArea1.getText().isEmpty()) {
            textArea2.setText(DictionaryManagement.translateApi(textArea1.getText()));
        } else {
            System.out.println("Error");
        }
    }
}
