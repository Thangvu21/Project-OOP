package Base;

public class Trie {

    public static void insertWord(TrieNode root, String word, String meaning) {
        TrieNode temp = root;
        for (int i = 0; i< word.length(); i++) {
            int index = word.toLowerCase().charAt(i) - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new TrieNode();
            }
            temp = temp.children[index];
        }
        temp.setIsWord();
        temp.setMeaning(meaning);
    }

    public static String searchWord(TrieNode root, String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.toLowerCase().charAt(i) - 'a';
            if (temp.children[index] == null) {
                return "Không tìm thấy!";
            }
            temp = temp.children[index];
        }
        return temp.getMeaning();
    }

    public static boolean startWith(TrieNode root, String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            if(!temp.containsKey(prefix.charAt(i))) {
                return false;
            }
            temp = temp.get(prefix.charAt(i));
        }
        return true;
    }
    public static boolean isEmpty(TrieNode temp) {
        for (int i = 0; i < 26; i++) {
            if (temp.children[i] != null) {
                return false;
            }
        }
        return true;
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
            if (isEmpty(root)) {
                root = null;
            }
            return root;
        }
        char ch = key.charAt(depth);
        root.children[ch - 'a'] = removeWord(root, key, depth + 1);

        if (isEmpty(root) && root.isWord() == false){
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
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                showTrie(root.children[i], prefix +
                        (char) ('a' + i));
            }
        }
    }

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        Trie.insertWord(root,"hello", "chao");
        Trie.insertWord(root, "house", "nha");
        Trie.insertWord(root, "houce", "nhe");
        String prefix = "";
        Trie.showTrie(root, prefix);

    }
}
