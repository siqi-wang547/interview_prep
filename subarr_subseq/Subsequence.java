package subarr_subseq;


public class Subsequence {

    /**
     * O(n^2) solution, L[j] = max(L[i]) + 1, where i < j && a[i] < a[j]
     * @param nums
     * @return
     */
    public int LIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public boolean increasingTripletSeq(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num <= first) first = num;
            else if (num <= second) second = num;
            else return true;
        }
        return false;
    }
}
