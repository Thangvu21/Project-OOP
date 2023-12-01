package com.example.demoDB;

import Base.DictionaryManagement;
import Base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController extends GeneralController {

    // -- ADD
    @FXML
    private TextField fieldWordAdd;

    @FXML
    private TextField filedExplainAdd;


    // -- MODIFY
    @FXML
    private TextField fieldWordModify;

    @FXML
    private TextField fieldExplainModify;

    // -- DELETE
    @FXML
    private TextField fieldDeleteWord;



    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void addWord() {
        String target = fieldWordAdd.getText();
        if (target.isEmpty() || DictionaryManagement.searchWord(target) != null) {
            showWarningAlert();
            return;
        }
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn thêm từ này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == yes) {
            Word word = new Word(target, filedExplainAdd.getText(), "");
            DictionaryManagement.addWord(word);
            wordList.add(word);
            fieldWordAdd.clear();
            filedExplainAdd.clear();

//            edit.setHtmlText("");
            alert = new Alert(Alert.AlertType.INFORMATION, "Bạn đã thêm thành công");
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyWord() {
        String target = fieldWordModify.getText();
        if (target.isEmpty() || DictionaryManagement.searchWord(target) == null) {
            showWarningAlert();
            return;
        }
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn sửa từ này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == yes) {
            DictionaryManagement.modifyWord(target, fieldExplainModify.getText(), "");
            for (Word word : wordList) {
                if (word.getWord_target().equals(target)) {
                    word.setWord_explain(fieldExplainModify.getText());
                    break;
                }
            }
            fieldWordModify.setText("");
            fieldExplainModify.setText("");
//            edit.setHtmlText("");
            alert = new Alert(Alert.AlertType.INFORMATION, "Bạn đã sửa thành công");
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void deleteWord() {
        String target = fieldDeleteWord.getText();
        if (target.isEmpty() || DictionaryManagement.searchWord(target) == null) {
            showWarningAlert();
            return;
        }
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == yes) {

            for (Word word : wordList) {
                if (word.getWord_target().equals(target)) {
                    wordList.remove(word);
                    break;
                }
            }
            DictionaryManagement.deleteWord(target);
            fieldDeleteWord.clear();
//            edit.setHtmlText("");

            alert = new Alert(Alert.AlertType.INFORMATION, "Bạn đã xóa thành công");
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Không có từ nào được chọn!");
        alert.showAndWait();
    }

    public void initEditController() {

    }
}
