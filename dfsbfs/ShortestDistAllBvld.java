package dfsbfs;

import java.util.*;

public class ShortestDistAllBvld {

    private static final int[][] DIRS = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

    /**
     * LC317. Shortest Distance from All Buildings
     * @Idea from every building, BFS every 0, since BFS will always find out the shortest distance, directly add up
     * @param grid input map
     * @return the best position for the new building, -1 if there's none
     *
     * A faster solution with pruning: https://discuss.leetcode.com/topic/46896/9-10ms-java-solution-beats-98
     */
    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 0 || n == 0) return -1;
        int[][] dist = new int[m][n]; // for every building, BFS every 0, add up the distance from every search
        int[][] reach = new int[m][n]; // for every 0 find out the reachable building number
        int bvldNum = 0; // find out building number

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    bvldNum++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});

                    boolean[][] visited = new boolean[m][n];
                    int level = 1; // distance from every building

                    while (!q.isEmpty()) {
                        int size = q.size();
                        for (int s = 0; s < size; s++) {
                            int[] cur = q.poll();

                            for (int[] dir: DIRS) {
                                int xx = cur[0] + dir[0], yy = cur[1] + dir[1];

                                if (xx >= 0 && xx < m && yy >= 0 && yy < n
                                        && grid[xx][yy] == 0 && !visited[xx][yy]) {
                                    dist[xx][yy] += level; // add up distances
                                    reach[xx][yy]++;
                                    visited[xx][yy] = true;
                                    q.offer(new int[]{xx, yy});
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE; // find out the min
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == bvldNum)
                    shortest = Math.min(shortest, dist[i][j]);
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }
}
