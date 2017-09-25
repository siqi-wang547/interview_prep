package backtrack;
import java.util.*;

public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        // boolean[][] visited = new boolean[m][n]; not right!
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, i, j, word, new boolean[m][n], 0))
                        // every time start a search, use a new visited matrix !!!
                        return true;
                }
            }
        }
        return false;
    }

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static boolean backtrack(char[][] board, int i, int j, String word, boolean[][] visited, int offset) {
        if (offset == word.length()) return true;
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n ||
                visited[i][j] || board[i][j] != word.charAt(offset))
            return false;
        boolean res = false;
        visited[i][j] = true;
        for (int[] dir: DIRS) {
            res = res || backtrack(board, i + dir[0], j + dir[1], word, visited, offset + 1);
            if (res) {
                visited[i][j] = false; // important!!! reset visited[i][j]
                return res;
            }
        }
        visited[i][j] = false; // important!!! reset visited[i][j]
        return res;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {{'A','B','C','E'}, {'S','F','E','S'}, {'A','D','E','E'}};
        System.out.println(exist(board, "ABCB"));
    }

    /**
     * LC212. Word Search II
     * @idea build a trie to store the words, then backtrack
     * @param board input char map
     * @param words input word array
     * @return words found in the map
     */
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtrack(board, root, i, j, res);
            }
        }
        return res;
    }

    private void backtrack(char[][] board, TrieNode node, int i, int j, List<String> res) {
        char c = board[i][j];
        if (c == '#' || node.next[c-'a'] == null) return; // here
        node = node.next[c-'a'];
        if (node.word.length() > 0) {
            res.add(node.word);
            node.word = "";
        }

        board[i][j] = '#'; // save the space for visited by changing the map itself
        if (i > 0) backtrack(board, node, i-1, j, res);
        if (i < board.length) backtrack(board, node, i+1, j, res);
        if (j > 0) backtrack(board, node, i, j-1, res);
        if (j < board[0].length) backtrack(board, node, i, j+1, res);
        board[i][j] = c; // here
    }

    class TrieNode {
        String word = "";
        TrieNode[] next = new TrieNode[26];
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode(), node = root;
        for (String word: words) {
            char[] w = word.toCharArray();
            for (char c: w) {
                if (node.next[c - 'a'] == null)
                    node.next[c - 'a'] = new TrieNode();
                node = node.next[c - 'a'];
            }
            node.word = word;
            node = root;
        }
        return root;
    }

}
