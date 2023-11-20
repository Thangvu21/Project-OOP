package Base;

import java.util.TreeMap;

public class TrieNode {
    public TreeMap<Character, TrieNode> children = new TreeMap<>();

    private String meaning;
    private boolean isWord;

    public TrieNode() {
        meaning = "";
        isWord = false;
    }
    public String getMeaning() {
        return meaning;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setIsWord() {
        isWord = true;
    }

    public void setIsWord(boolean word) {
        isWord = word;
    }

}
