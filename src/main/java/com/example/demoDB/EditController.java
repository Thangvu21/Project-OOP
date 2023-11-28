package com.example.demoDB;

import Base.DictionaryManagement;
import Base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController extends SearchController {

    // -- ADD
    @FXML
    private TextField fieldWordAdd;

    @FXML
    private TextField filedExplainAdd;

    @FXML
    private Button add;

    // -- MODIFY
    @FXML
    private TextField fieldWordModify;

    @FXML
    private TextField fieldExplainModify;

    @FXML
    private Button modify;

    // -- DELETE
    @FXML
    private TextField fieldDeleteWord;

    @FXML
    private Button delete;


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void addWord() {
        String target = fieldWordAdd.getText();
        if (DictionaryManagement.searchWord(target) != null) {
            Alert.AlertType.valueOf("Từ này đã tồn tại vui lòng thêm từ khác hoặc sang phần sửa từ");
        } else {
            Alert.AlertType.valueOf("Hãy nhập nghĩa của từ");
            String explain = filedExplainAdd.getText();
            Word word = new Word(target, explain);
            DictionaryManagement.addWord(word);
            Alert.AlertType.valueOf("Nhập thành công");
        }
    }

    @FXML
    public void modifyWord() {
        String target = fieldWordAdd.getText();
        if (DictionaryManagement.searchWord(target) == null) {
            Alert.AlertType.valueOf("Từ này không tồn tại vui lòng sửa từ khác");
        } else {
            Alert.AlertType.valueOf("Hãy nhập nghĩa của từ");
            String explain = filedExplainAdd.getText();
            Word word = new Word(target, explain);
            // mảng obser kia sẽ thêm vào
            DictionaryManagement.modifyWord(target, explain, "");
            Alert.AlertType.valueOf("Nhập thành công");
        }
    }

    @FXML
    public void deleteWord() {
        String target = fieldWordAdd.getText();
        if (DictionaryManagement.searchWord(target) == null) {
            Alert.AlertType.valueOf("Từ này không tồn tại vui lòng xóa từ khác");
        } else {
            DictionaryManagement.deleteWord(target);
            // mảng xóa từ đi
            Alert.AlertType.valueOf("Xóa thành công");
        }
    }
}
