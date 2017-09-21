package array;
import java.util.*;

public class MissingRanges {
    static public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long next = lower;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < next) continue;
            else if (nums[i] == next) next++;
            else {
                res.add(getRange(next, nums[i] - 1));
                // if (nums[i] == Integer.MAX_VALUE) break;
                next = (long) nums[i] + 1; // convert to long to avoid Integer.Max + 1 = Integer.MIN
            }
        }
        if (next <= upper) res.add(getRange(next, upper));
        return res;
    }

    static private String getRange(long i, long j) {
        return (i == j) ? String.valueOf(i): String.format("%d->%d", i, j);
    }

    public static void main(String[] args) {
        // corner case 0
        int[] input = new int[]{Integer.MAX_VALUE};
        System.out.println(findMissingRanges(input, 0, Integer.MAX_VALUE));
    }

    /**
     * Corner cases
     * Test case 1:
     A = []
     lower = -2147483648
     upper = 2147483647
     Test case 2:
     A = []
     lower = 0
     upper = 2147483647
     Test case 3:
     A = [-2147483648]
     lower = -2147483648
     upper = 2147483647
     */
}
