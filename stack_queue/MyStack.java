package stack_queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC 225. Implement Stack using Queues
 * Problem: use standard queue methods to implement a stack
 */
public class MyStack {
    Queue<Integer> q;

    public MyStack() {
        q = new ArrayDeque<>();
    }

    public void push(int x) {
        q.offer(x);
    }

    public int pop() {
        int s = q.size();
        for (int i = 1; i < s; i++) {
            q.offer(q.poll());
        }
        return q.poll();
    }

    public int peek() {
        int s = q.size();
        for (int i = 1; i < s; i++)
            q.add(q.poll());
        int r = q.poll();
        q.add(r);
        return r;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
