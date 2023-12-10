package com.example.demoDB;

import Base.DictionaryManagement;
import Base.Voice;
import Base.Word;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends GeneralController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private TextArea textArea;
    @FXML
    private WebView viewWord;

    @FXML
    private Button pronounce;

    @FXML
    private TableView<Word> tableWord;

    @FXML
    private TableColumn<Word, String> wordColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            wordColumn.setCellValueFactory(new PropertyValueFactory<>("word_target"));

            tableWord.setItems(wordList);

            FilteredList<Word> filteredData = new FilteredList<>(wordList, b -> true);

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
                searchField.setText(tableWord.getSelectionModel().getSelectedItem().word_target);
                showWordDetails(t1);
                historyWord.add(t1);
            }
        });
    }

    @FXML
    public void setPronounce() {
            pronounce.setOnAction(event -> {
                try {
                    if (searchField.getText() != null) {
                        Voice.speakWord(searchField.getText(), languageEn);
                    } else {
                        System.out.println("error");
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
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
                    });
                }
            }).start();
        }
    }


    public void clearSearchField() {
        searchField.clear();
        searchField.clear();
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
        textArea.clear();
    }
}
