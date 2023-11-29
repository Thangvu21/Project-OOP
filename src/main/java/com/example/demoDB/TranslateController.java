package com.example.demoDB;

import Base.DictionaryManagement;
import Base.Translator;
import Base.Voice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends GeneralController {
    @FXML
    private TextArea textArea1;

    @FXML
    private TextField textArea2;

    private boolean status = false;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void translate() {
        if (status == false) {
            if (!textArea1.getText().isEmpty()) {
                try {
                    textArea2.setText(Translator.translate(langEn, langVi, textArea1.getText()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Error");
            }
        } else {
            if (!textArea1.getText().isEmpty()) {
                try {
                    textArea2.setText(Translator.translate(langVi, langEn, textArea1.getText()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Error");
            }
        }

    }

    @FXML
    public void speak1() {
        if (status == false) {
            if (!textArea1.getText().isEmpty()) {
                try {
                    Voice.speakWord(textArea1.getText(), languageEn);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            try {
                Voice.speakWord(textArea1.getText(), languageVn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void speak2() {
        if (status == false) {
            if (!textArea1.getText().isEmpty()) {
                try {
                    Voice.speakWord(textArea2.getText(), languageVn);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            try {
                Voice.speakWord(textArea2.getText(), languageEn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void transfer1() {
        status = false;
        textArea1.clear();
    }

    @FXML
    public void transfer2() {
        status = true;
        textArea1.clear();
    }
}
