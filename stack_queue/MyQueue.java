package stack_queue;

import java.util.Stack;

/**
 * LC232: Implement Queue using Stacks
 * Problem: implement a queue using standard stack and only stack operations
 * Amortized analysis https://leetcode.com/problems/implement-queue-using-stacks/solution/
 * Consider a seq of ops: push,push,...,push,pop,pop,...,pop
 * Total time = n (push) + 2n (first pop) + n-1 (rest of pops) = O(2n)
 * Amortized time = O(2n) / 2n = O(1)
 * Push O(1) and pop amortized O(1)
 */
public class MyQueue {

    Stack<Integer> pushSt;
    Stack<Integer> popSt;

    public MyQueue() {
        pushSt = new Stack<>();
        popSt = new Stack<>();
    }

    public void offer(int x) {
        pushSt.push(x);
    }

    public int poll() {
        if (popSt.isEmpty()) {
            while (!pushSt.isEmpty())
                popSt.push(pushSt.pop());
        }
        return popSt.pop();
    }

    public int peek() {
        if (popSt.isEmpty()) {
            while (!pushSt.isEmpty())
                popSt.push(pushSt.pop());
        }
        return popSt.peek();
    }

    public boolean empty() {
        return pushSt.isEmpty() && popSt.isEmpty();
    }
}
