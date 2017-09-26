package array;

public class FindCelebrity {

    private boolean knows(int i, int j) { return false; }

    /**
     * LC277. Find the Celebrity
     * @param n input number of people
     * @return the number for the celebrity if present, -1 otherwise
     */
    public int findCelebrity(int n) {
        int candidate = 0;

        // one pass to findout the celebrity candidate, all person will point to him while he points no one
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) candidate = i;
        }

        // one pass testing if candidate found is a real celebrity
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate)))
                return -1;
        }
        return candidate;
    }
}
