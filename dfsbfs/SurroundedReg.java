package dfsbfs;
import java.util.*;

public class SurroundedReg {
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return ;
        int m = board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                q.add(new int[]{i, 0});
                board[i][0] = '*';
            }
            if (board[i][n-1] == 'O') {
                q.add(new int[]{i, n-1});
                board[i][n-1] = '*';
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                q.add(new int[]{0, i});
                board[0][i] = '*';
            }
            if (board[m-1][i] == 'O') {
                q.add(new int[]{m-1, i});
                board[m-1][i] = '*';
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];
            if (x - 1 >= 0 && board[x-1][y] == 'O') {
                q.add(new int[]{x-1, y});
                board[x-1][y] = '*';
            }
            if (x + 1 < m && board[x+1][y] == 'O') {
                q.add(new int[]{x+1, y});
                board[x+1][y] = '*';
            }
            if (y - 1 >= 0 && board[x][y-1] == 'O') {
                q.add(new int[]{x, y-1});
                board[x][y-1] = '*';
            }
            if (y + 1 < n && board[x][y+1] == 'O') {
                q.add(new int[]{x, y+1});
                board[x][y+1] = '*';
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '*') board[i][j] = 'O';
            }
        }
    }

    public static void main(String[] args) {
        char[][] input = new char[][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(input);
        for (char[] row: input) System.out.println(Arrays.toString(row));
    }
}
