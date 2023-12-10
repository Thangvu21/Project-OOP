package com.example.demoDB;

import Base.DictionaryManagement;
import Base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends GeneralController implements Initializable {

    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private AnchorPane historyPane;
    @FXML
    private AnchorPane settingPane;

    private SearchController searchController;

    private HistoryController historyController;

    private EditController editController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.initDictionary();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
            historyPane = loader.load();
            historyController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            gamePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("google.fxml"));
            translatePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("setting.fxml"));
            settingPane = loader.load();
            editController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    @FXML
    private void setSearch() throws IOException {
        searchController.clearSearchField();
        setMainContent(searchPane);
    }

    @FXML
    private void setGoogle() throws IOException {
        setMainContent(translatePane);
    }

    @FXML
    private void setHistory() throws IOException {
        historyController.clearSearchField();
        historyController.initHistory();
        setMainContent(historyPane);
    }

    @FXML
    private void setSetting() throws IOException {
        editController.initEditController();
        setMainContent(settingPane);
    }

    @FXML
    private void setGame() throws IOException {
        setMainContent(gamePane);
    }
}
