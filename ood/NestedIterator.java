package ood;

import java.util.*;

interface NestedInteger {
    /**
     * @return true if this NestedInteger holds a single integer, rather than a nested list
     */
    public boolean isInteger();

    /**
     * @return the single integer that this NestedInteger holds, if it holds a single integer
     *         null if this NestedInteger holds a nested list
     */
    public Integer getInteger();

    /**
     * @return the nested list that this NestedInteger holds, if it holds a nested list
     *         null if this NestedInteger holds a single integer
     */
    public List<NestedInteger> getList();

    /**
     * Instance of class that implements this interface: [1,[4,[6]]]
     */
}

/**
 * LC341. Flatten Nested List Iterator
 */
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = addList(nestedList);
    }

    private List<Integer> addList(List<NestedInteger> nestedList) {
        List<Integer> res = new LinkedList<>();
        for (NestedInteger ni: nestedList) {
            if (ni.isInteger()) res.add(ni.getInteger());
            else res.addAll(addList(ni.getList()));
        }
        return res;
    }

    @Override
    public Integer next() {
        return list.remove(0);
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }
}
