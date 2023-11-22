package Base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Trie {
    public static void insertWord(TrieNode root, String word, String meaning, String pronounce) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (!temp.children.containsKey(word.charAt(i))) {
                temp.children.put(word.charAt(i),new TrieNode());
            }
            temp = temp.children.get(word.charAt(i));
        }
        temp.setIsWord();
        temp.setMeaning(meaning);
        temp.setPronounce(pronounce);
    }

    public static Word searchWord(TrieNode root, String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (!temp.children.containsKey(word.charAt(i))) {
                return null;
            }
            temp = temp.children.get(word.charAt(i));
        }
        return new Word(word, root.getMeaning(), root.getPronounce());
    }

    public static boolean startWith(TrieNode root, String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!temp.children.containsKey(prefix.charAt(i))) {
                return false;
            }
            temp = temp.children.get(prefix.charAt(i));
        }
        return true;
    }

    public static void modifyWord(TrieNode root, String word, String meaning, String pronounce) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (!temp.children.containsKey(word.charAt(i))) {
                System.out.println("Không tìm thấy!");
                return;
            }
            temp = temp.children.get(word.charAt(i));
        }
        temp.setMeaning(meaning);
        temp.setPronounce(pronounce);
    }

    public static TrieNode removeWord(TrieNode root, String key, int depth) {
        // If tree is empty
        if (root == null) {
            return null;
        }

        // ki tu cuoi cung
        if (depth == key.length()) {
            if(root.isWord()) {
                root.setIsWord(false);
            }
            // neu ko co prefix
            if (root.children.isEmpty()) {
                root = null;
            }
            return root;
        }
        char ch = key.charAt(depth);
        TrieNode temp = root.children.get(ch);
        temp = removeWord(root, key, depth + 1);

        if (root.children.isEmpty() && root.isWord() == false){
            root = null;
        }

        return root;
    }

    public static void showTrie(TrieNode root, String prefix) {
        if (root == null) {
            return;
        }
        if (root.isWord()) {
            System.out.print(prefix + " " + root.getMeaning() + "\n");
        }
        for (char ch : root.children.keySet()) {
            showTrie(root.children.get(ch), prefix +
                    ch);
        }
    }

    public static void setView (TrieNode root, String prefix, ObservableList<Word> view) {
        if (root == null) {
            return;
        }
        if (root.isWord()) {
            view.add(new Word(prefix, root.getMeaning(), root.getPronounce()));
        }
        for (char ch : root.children.keySet()) {
            setView(root.children.get(ch), prefix +
                    ch, view);
        }
    }

    public static ObservableList<Word> getListView (TrieNode root, String prefix) {
        ObservableList<Word> view = FXCollections.observableArrayList();
        setView(root, prefix, view);
        return view;
    }

    public static void main(String[] args) {
        TrieNode root = new TrieNode();

        System.out.println(Trie.startWith(root, "abc"));
    }
}
