package dfsbfs;

public class Islands {

    /**
      ** LC200. number of islands
      ** idea: for every point in the grid, dfs marking visited point, one round of search is an island, increment the number
      ** improvement: using extra space for visited[][] is not necessary, marking visited points in grid as '0' directly
    */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int cnt = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, visited, i, j);
                    cnt++;
                }
            }
        }
        // for (int i = 0; i < visited.length; i++) System.out.println(Arrays.toString(visited[i]));
        return cnt;
    }
    
    private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        if (visited[i][j] || grid[i][j] == '0') return;
        visited[i][j] = true;
        if (i > 0) dfs(grid, visited, i - 1, j);
        if (i < grid.length - 1) dfs(grid, visited, i + 1, j);
        if (j > 0) dfs(grid, visited, i, j - 1);
        if (j < grid[0].length - 1) dfs(grid, visited, i, j + 1);
    }
}