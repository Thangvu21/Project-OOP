package Base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Base.Voice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class DictionaryManagement extends Dictionary {

    public static void initDictionary() {
        Connect.importDataInTrie("av", root);
//        String fileName = "dictionary.txt";
//
//        URL resourceURL = DictionaryManagement.class.getResource("/database/" + fileName);
//
//        if (resourceURL != null) {
//            String filePath = new File(resourceURL.getFile()).getPath();
//            DictionaryManagement.insertFromTxt(filePath);
//        } else {
//            System.out.println("Không thực hiện được");
//        }
    }

    public static void insertFromTxt(String path) {
        String word_explain = "", word_target = "";
        try {
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && line.charAt(0) == '@') {
                    if (!word_explain.equals("")) {
                        Trie.insertWord(root, word_target, word_explain.substring(0,
                                word_explain.length() - 2), "");
                        word_explain = "";
                    }
                    word_target = line.substring(1);
                } else if (!line.isEmpty() && line.charAt(0) == '-') {
                    word_explain += (line.substring(1) + ", ");
                }
            }
            file.close();
            br.close();
            System.out.println("Read successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addWord(Word word) {
        Trie.insertWord(root, word.getWord_target(), word.getWord_explain(), word.getPronounce());
    }
    public static void deleteWord(String word) {
        Trie.removeWord(root, word, 0);
    }

    public static void modifyWord(String word, String explain, String pronounce) {
        Trie.modifyWord(root, word, explain, pronounce);
    }


    public static Word searchWord(String word) {
        Word result = Trie.searchWord(root, word);
        return result;
    }

    public static boolean startWith(String prefix) {
        return Trie.startWith(root, prefix);
    }

    public static ObservableList<String> getLookupWord(String prefix) {
        ObservableList<String> newOB = FXCollections.observableArrayList();
        if (Trie.startWith(root, prefix)) {
            newOB = Trie.getListView(root, prefix);
        } else {
            System.out.println("Not values!");
        }
        return newOB;
    }

    public static ObservableList<Word> getLookupWordW(String prefix) {
        ObservableList<Word> newOB = FXCollections.observableArrayList();
        if (Trie.startWith(root, prefix)) {
            newOB = Trie.getListViewW(root, prefix);
        } else {
            System.out.println("Not values!");
        }
        return newOB;
    }

    public static void lookupWord(String word) {
        if (Trie.startWith(root, word)) {
            Trie.showTrie(root, word);
        } else {
            System.out.println("Not values!");
        }
    }


    public static void main(String[] args) {
        DictionaryManagement.addWord(new Word("haha", "hihi", ""));
        DictionaryManagement.deleteWord("haha");
        if (DictionaryManagement.searchWord("haha") == null) {
            System.out.println("xóa chuẩn");
        } else {
            System.out.println("xóa lỗi");
        }
        DictionaryManagement.lookupWord("");
//        DictionaryManagement.initDictionary();
//        ObservableList<Word> observableList = getLookupWordW("");
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(observableList.get(i).getWord_target());
//        }
//        DictionaryManagement.pronounceWord("hello");
    }
}
