package com.example.demoDB;

import Base.Voice;
import Base.Word;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

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

    @FXML
    private WebView viewWord;

    private ObservableList<Word> history = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word_target"));
        tableWord.setItems(history);
        tableWord.getSelectionModel().selectedItemProperty().addListener((observableValue, wordSearchModel, t1) -> {
            textAreaHistory.setMouseTransparent(true);
            if(tableWord.getSelectionModel().getSelectedItem()!=null) {
                textAreaHistory.setText(tableWord.getSelectionModel().getSelectedItem().getWord_explain());
                showWordDetails(t1);
            }
        });
    }

    public void initHistory() {
        history.clear();
        for(Word word : historyWord) {
            history.add(word);
        }
    }

    @FXML
    public void setPronounce() {
        Word word = tableWord.getSelectionModel().getSelectedItem();
        try {
            Voice.speakWord(word.getWord_target(), languageEn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String generateHTML(Word word) {
        String css = "<style>"
                + "body { font-family: Arial, sans-serif; margin: 10px; }"
                + "h1 { color: navy; margin-bottom: 0; }"
                + "p { margin-top: 5px; font-size: 14px; }"
                + ".word-container { bolder: 1px solid #ddd; padding: 15px;}"
                + "</style>";

        String html = "<html><head>" + css + "</head><body>"
                + "<div class='word-container'>"
                + "<h1>" + word.getWord_target() + "</h1>"
                + "<p>" + word.getPronounce() + "</p>"
                + "<p>" + word.getWord_explain() + "</p>"
                + "</div>" + "</body></html>";

        return html;
    }

    public void showWordDetails(Word word) {
        if (word != null) {
            new Thread(() -> {
                try {
                    String wordDetails = generateHTML(word);
                    Platform.runLater(() -> {
                        viewWord.getEngine().loadContent(wordDetails);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> {
                    });
                }
            }).start();
        }
    }

    public void clearSearchField() {
        history.clear();
        textAreaHistory.clear();
        new Thread(() -> {
            try {
                Platform.runLater(() -> {
                    viewWord.getEngine().loadContent("");
                });
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                });
            }
        }).start();
        initHistory();
    }
}
