package com.example.demoDB;

import Base.Connect;
import Base.DictionaryManagement;
import Base.TrieNode;
import Base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class GeneralController {

    public final String languageEn = "en-gb";

    public final String languageVn = "vi-vn";

    public final String langEn = "en";

    public final String langVi = "vi";

    protected static ObservableList<Word> wordList = Connect.importDataInObser("av");
    protected static Set<Word> historyWord = new HashSet<>();;


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
