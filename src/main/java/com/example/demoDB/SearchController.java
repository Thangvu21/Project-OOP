package com.example.demoDB;

import Base.DictionaryManagement;
import Base.Voice;
import Base.Word;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private TextArea textArea;
    @FXML
    private WebView viewWord;

    @FXML
    private Button pronounce;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private Button save;

    @FXML
    private TableView<Word> tableWord;

    @FXML
    private TableColumn<Word, String> wordColumn;

    private ObservableList<Word> filteredWords = DictionaryManagement.getLookupWordW("");

    private String pronounceWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            wordColumn.setCellValueFactory(new PropertyValueFactory<>("word_target"));

            tableWord.setItems(filteredWords);

            FilteredList<Word> filteredData = new FilteredList<>(filteredWords, b -> true);

            searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Word -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String keyWord = newValue;

                    return Word.getWord_target().startsWith(keyWord);
                });
            }));
        SortedList<Word> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableWord.comparatorProperty());

        tableWord.setItems(sortedData);

        tableWord.getSelectionModel().selectedItemProperty().addListener((observableValue, wordSearchModel, t1) -> {
            textArea.setMouseTransparent(true);
            if(tableWord.getSelectionModel().getSelectedItem()!=null) {
                textArea.setText(tableWord.getSelectionModel().getSelectedItem().getWord_explain());
                showWordDetails(t1);
            }
        });
    }

    @FXML
    public void setPronounce() {
        pronounce.setOnAction(event -> {
            Word word = tableWord.getSelectionModel().getSelectedItem();
            if (word != null) {
                DictionaryManagement.pronounceWord(word.getWord_target());
            }
        });

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
                        viewWord.getEngine().loadContent("Error");
                    });
                }
            });
        }
    }


}
