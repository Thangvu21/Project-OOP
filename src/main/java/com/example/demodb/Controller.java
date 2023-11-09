package com.example.demodb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button search_button;
    @FXML
    private Button save_button;
    @FXML
    private Button history_button;
    @FXML
    private Button google_button;
    @FXML
    private Button setting_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void setSearch(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("search.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void setGoogle(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("google.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void setHistory(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("history.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void setSetting(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("setting.fxml"));
        borderPane.setCenter(view);
    }
}
