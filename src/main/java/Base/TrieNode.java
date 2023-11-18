package Base;

public class TrieNode {
    static int ALPHABET_SIZE = 26;
    public TrieNode[] children = new TrieNode[ALPHABET_SIZE];
    public String meaning;
    public boolean isWord;

    public TrieNode() {
        meaning = "";
        isWord = false;
    }

    public boolean containsKey(char ch) {
        return (children[ch - 'a'] != null);
    }

    public TrieNode get(char ch) {
        return children[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        children[ch - 'a'] = node;
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
