package com.example.demoDB;

import Base.Word;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {

    String key = "word_target";

    @FXML
    private TableView<Word> wordListTable;
    @FXML
    private TableColumn<Word, String> ListViewKey;
    @FXML
    private TextField searchField;
    @FXML
    private WebView webView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // lấy dữ liệu lọc và hiển thị
        ListViewKey.setCellValueFactory(new PropertyValueFactory<>(key));

        wordListTable.setItems(searchList);

        FilteredList<Word> filteredData = new FilteredList<>(searchList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Word -> {
                // if not found data
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String keyWord = newValue;

                if (Word.getWord_target().startsWith(keyWord)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Word> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(wordListTable.comparatorProperty());

        wordListTable.setItems(sortedData);

        SelectionModel<Word> wordSelection = wordListTable.getSelectionModel();

        Word word = wordSelection.getSelectedItem();

        if (word != null) {
            System.out.println(word.getWord_explain());
        }
    }

    public String generateHTML(Word word) {
        String css = "<style>"
                + "body { font-family: Arial, sans-serif; margin: 10px; }"
                + "h1 { color: navy; margin-bottom: 0; }"
                + "p { margin-top: 5px; font-size: 14px; }"
                + ".word-container { bolder: 1px solid #ddd; padding: 15px}"
                + "</style>";

        String html = "<html><head>" + css + "</head><body>"
                    + "<div class='word-container'>"
                    + "<h1>" + word.getWord_target() + "</h1>"
                    + "<p>" + word.getPronounce() + "</p>"
                    + "<p>" + word.getWord_explain() + "</p>"
                    + "</div>" + "</body></html>";
        return html;
    }


}
