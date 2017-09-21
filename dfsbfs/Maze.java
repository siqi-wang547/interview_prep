package dfsbfs;

import java.util.*;

public class Maze {

    class Point {
        int x, y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * LC490 the maze, BFS solution
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(start[0], start[1]));
        visited[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int[] dir : dirs) {
                int xx = p.x, yy = p.y;
                while (xx >= 0 && xx < m && yy >= 0 && yy < n && maze[xx][yy] == 0) {
                    xx += dir[0];
                    yy += dir[1];
                }
                xx -= dir[0];
                yy -= dir[1];
                if (visited[xx][yy]) continue;
                if (xx == destination[0] && yy == destination[1]) return true;
                visited[xx][yy] = true;
                q.offer(new Point(xx, yy));
            }
        }

        return false;
    }

    class Point2 extends Point {
        int l;

        public Point2(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.l = l;
        }
    }

    /**
     * LC505 the maze ii, BFS with PQ (Dijkstra)
     * Dijkstra's algo: http://www.cnblogs.com/biyeymyhjob/archive/2012/07/31/2615833.html
     * @param maze
     * @param start
     * @param des
     * @return
     */
    public int shortestDistance(int[][] maze, int[] start, int[] des) {
        int m = maze.length, n = maze[0].length;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] length = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                length[i][j] = Integer.MAX_VALUE;
        }
        PriorityQueue<Point2> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.l));
        pq.offer(new Point2(start[0], start[1], 0));
        while (!pq.isEmpty()) {
            Point2 p = pq.poll();
            // System.out.printf("Poll point: p.x=%d, p.y=%d, p.l=%d\n", p.x, p.y, p.l);
            if (length[p.x][p.y] <= p.l) continue;
            length[p.x][p.y] = p.l;
            for (int[] dir: dirs) {
                int xx = p.x, yy = p.y, ll = p.l;
                while (xx >= 0 && xx < m && yy >= 0 && yy < n && maze[xx][yy] == 0) {
                    xx += dir[0];
                    yy += dir[1];
                    ll++;
                }
                xx -= dir[0];
                yy -= dir[1];
                ll--;
                // System.out.printf("Offer point: p.x=%d, p.y=%d, p.l=%d\n", p.x, p.y, p.l);
                pq.offer(new Point2(xx, yy, ll));
            }
        }
        return length[des[0]][des[1]] == Integer.MAX_VALUE ? -1 : length[des[0]][des[1]];
    }
}
