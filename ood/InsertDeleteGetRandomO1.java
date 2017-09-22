package ood;

import java.util.*;

interface InsertDeleteGetRandomO1 {
    public boolean insert(int val);
    public boolean remove(int val);
    public int getRandom();
}

/**
 * LC380. Insert Delete GetRandom O(1)
 * Design a data structure that supports all following operations in average O(1) time.

 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
class RandomizedSet implements InsertDeleteGetRandomO1 {
    private List<Integer> list;
    private Map<Integer, Integer> map; // use a map to store the value and index in the arraylist
    private Random rand;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int pos = map.get(val), last = list.size() - 1;
        if (pos < last) { // important cannot save this step
            list.set(pos, list.get(last));
            map.put(list.get(last), pos);
        }
        list.remove(last);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }
}

/**
 * LC381. Insert Delete GetRandom O(1) duplicate allowed
 * Design a data structure that supports all following operations in average O(1) time.

 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
class RandomizedCollection implements InsertDeleteGetRandomO1 {

    private List<Integer> arr;
    private Map<Integer, LinkedHashSet<Integer>> map;
    private Random rand;

    public RandomizedCollection() {
        arr = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    @Override
    public boolean insert(int val) {
        boolean contains = map.containsKey(val);
        if (!contains) {
            map.put(val, new LinkedHashSet<>());
        }
        map.get(val).add(arr.size());
        arr.add(val);
        return !contains;
    }

    // 1, 1, 2, 3 -> (1: 0, 1), (2: 2), (3: 3)

    @Override
    public boolean remove(int val) {
        boolean contains = map.containsKey(val);
        if (contains) {
            int lastIdx = arr.size() - 1, lastElem = arr.get(lastIdx);
            int valIdx = map.get(val).iterator().next();
            map.get(val).remove(valIdx);
            // must be here, otherwise in edge case where lastElem == val, the lastIdx won't be able to added
            // edge case (insert 4,3,4,2,4) and then (remove 4,3,4,4)
            if (valIdx < lastIdx) {
                map.get(lastElem).remove(lastIdx);
                map.get(lastElem).add(valIdx);
                arr.set(valIdx, lastElem);
            }
            arr.remove(lastIdx);
            if (map.get(val).isEmpty()) map.remove(val);
        }
        return contains;
    }

    @Override
    public int getRandom() {
        return arr.get(rand.nextInt(arr.size()));
    }
}
