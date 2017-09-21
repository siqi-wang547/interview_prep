package topologicalsort;

import java.util.*;

public class CourseSchedule {
    /**
     * LC207. Course Schedule
     * @param num
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int num, int[][] prerequisites) {
        int[][] matrix = new int[num][num];
        int[] inDegree = new int[num];

        for (int[] pair: prerequisites) {
            int course = pair[0];
            int pre = pair[1];
            if (matrix[pre][course] == 0) // 可以不要
                inDegree[course]++;
            matrix[pre][course] = 1;
        }

        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int course = q.poll();
            count++;
            for (int i = 0; i < num; i++) {
                if (matrix[course][i] != 0) {
                    if (--inDegree[i] == 0)
                        q.offer(i);
                }
            }
        }
        return count == num;
    }

    /**
     * LC210. Course Schedule II
     * @param num
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int num, int[][] prerequisites) {
        int[][] matrix = new int[num][num];
        int[] inDegree = new int[num];

        for (int[] pair: prerequisites) {
            int course = pair[0], pre = pair[1];
            inDegree[course]++;
            matrix[pre][course] = 1;
        }

        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        int[] order = new int[num];

        for (int i = 0; i < num; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int course = q.poll();
            order[cnt++] = course;
            for (int i = 0; i < num; i++) {
                if (matrix[course][i] != 0) {
                    if (--inDegree[i] == 0) q.offer(i);
                }
            }
        }

        return (cnt == num) ? order: new int[0];
    }
}
