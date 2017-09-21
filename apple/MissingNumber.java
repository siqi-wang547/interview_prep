package apple;

import java.util.Arrays;

/**
 * LC268: given an array of length n containing only numbers from [0:n]
 * find out the missing number
 */
public class MissingNumber {

    public static int missingNumXOR(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    public static int missingNumSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length, sum = len * (len + 1) / 2;
        for (int i: nums) sum -= i;
        return sum;
    }

    public static int missingNumBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == mid) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
