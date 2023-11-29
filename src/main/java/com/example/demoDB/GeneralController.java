package com.example.demoDB;

import Base.Connect;
import Base.DictionaryManagement;
import Base.TrieNode;
import Base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GeneralController {

    public final String languageEn = "en-gb";

    public final String languageVn = "vi-vn";

    public final String langEn = "en";

    public final String langVi = "vi";

    protected ObservableList<Word> wordList = Connect.importDataInObser("av");
    protected ObservableList<Word> historyWord = FXCollections.observableArrayList();;



    TrieNode root = new TrieNode();

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addWordInHistory(Word word) {
        historyWord.add(word);
        System.out.println("add success");
    }

    public ObservableList<Word> getHistoryWord() {
        return historyWord;
    }
}
