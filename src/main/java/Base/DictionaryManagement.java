package Base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class DictionaryManagement extends Dictionary {

    public static void initDictionary() {
        Connect.importDataInTrie("av", root);
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
                                word_explain.length() - 2));
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

    public static void deleteWord(String word) {
        Trie.removeWord(root, word, 0);
    }

    public static void modifyWord(String word, String explain) {
        Trie.modifyWord(root, word, explain);
    }

    public static void pronounceWord(String Word) {
        Voice.textToSpeed(Word);
    }

    public static String translateApi(String sentence) {
        try {
            return Translator.translate("en", "vi", sentence);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String searchWord(String word) {
        return Trie.searchWord(root, word);
    }

    public static void lookupWord(String word) {
        if (Trie.startWith(root, word)) {
            Trie.showTrie(root, word);
        } else {
            System.out.println("Not values!");
        }
    }

    public static void showDictionary() {
        Trie.showTrie(root,"");
    }


    public static void main(String[] args) {
        String fileName = "dictionary.txt";

        URL resourceURL = DictionaryManagement.class.getResource("/database/" + fileName);

        if (resourceURL != null) {
            String filePath = new File(resourceURL.getFile()).getPath();
            DictionaryManagement.insertFromTxt(filePath);
        } else {
            System.out.println("Không thực hiện được");
        }
//        DictionaryManagement.initDictionary();
        System.out.println(DictionaryManagement.searchWord("hello"));
    }
}
