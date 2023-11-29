package com.example.demoDB;

import Base.Voice;
import Base.Word;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController extends GeneralController implements Initializable {
    @FXML
    private TableView<Word> tableWord;

    @FXML
    private TableColumn<Word, String> wordColumn;

    @FXML
    private TextArea textAreaHistory;

    @FXML
    private Button pronounce;

    private ObservableList<Word> history = getHistoryWord();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word_target"));

        tableWord.setItems(history);
        System.out.println(historyWord.size());
    }

    @FXML
    public void selectHistory() {
        tableWord.getSelectionModel().selectedItemProperty().addListener((observableValue, wordSearchModel, t1) -> {
            textAreaHistory.setMouseTransparent(true);
            if(tableWord.getSelectionModel().getSelectedItem()!=null) {
                textAreaHistory.setText(tableWord.getSelectionModel().getSelectedItem().getWord_explain());
            }
        });
    }

    @FXML
    public void setPronounce() {
        Word word = tableWord.getSelectionModel().getSelectedItem();
        pronounce.setOnAction(event -> {
            try {
                if (word != null) {
                    Voice.speakWord(word.getWord_target(), languageEn);
                } else {
                    System.out.println("error");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
