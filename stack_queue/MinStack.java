package stack_queue;

import java.util.*;

/**
 * LC155. Min Stack
 * Problem: design a data structure to support O(1) pop, peek, push, getMin
 */
public class MinStack {

    /**
     * Two stack solution: use an extra minstack to store the smallest value
     * @param <T>
     */
    class MinStack2<T extends Comparable<T>> {
        private Stack<T> stack = new Stack<T>();
        private Stack<T> minStack = new Stack<T>();

        public void push(T t) {
            stack.push(t);
            if (minStack.isEmpty() || t.compareTo(minStack.peek()) <= 0)
                minStack.push(t);
        }

        public T pop() {
            T tmp = stack.pop();
            if (tmp.equals(minStack.peek()))
                minStack.pop();
            return tmp;
        }

        public T peek() {
            return stack.peek();
        }

        public T getMin() {
            return minStack.peek();
        }
    }

    /**
     * One stack solution: keep track of the min value and only keeps the diff in the stack
     */
    class MinStack1 {
        private long min;
        private Stack<Long> st;

        /** initialize your data structure here. */
        public MinStack1() {
            min = 0L;
            st = new Stack<>();
        }

        public void push(int x) {
            if (st.isEmpty()) {
                st.push(0L);
                min = x;
            } else {
                st.push(x - min);
                if (x < min) min = x;
            }
        }

        public void pop() {
            long peek = st.pop();
            if (peek < 0) min -= peek;
        }

        public int top() {
            long peek = st.peek();
            if (peek > 0) return (int)(peek + min);
            return (int) min;
        }

        public int getMin() {
            return (int) min;
        }
    }

}
