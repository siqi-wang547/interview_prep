package trie;

class Trie {

    private static int R = 26;
    private TrieNode root;

    class TrieNode {
        private String str;
        private TrieNode[] children;

        public TrieNode() {
            str = "";
            children = new TrieNode[R];
        }

        public TrieNode getChild(int i) {
            return children[i];
        }

        public void setChild(int i, TrieNode node) {
            children[i] = node;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String s) {
            str = s;
        }

    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            int i = c - 'a';
            if (node.getChild(i) == null) {
                node.setChild(i, new TrieNode());
                // node.getChild(i).setString(node.)
            }
            node = node.getChild(i);
        }
        node.setStr(word);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            int i = c - 'a';
            if (node.getChild(i) == null) return false;
            node = node.getChild(i);
        }
        return node.getStr().equals(word);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c: prefix.toCharArray()) {
            int i = c - 'a';
            if (node.getChild(i) == null) return false;
            node = node.getChild(i);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */