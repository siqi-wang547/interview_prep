package subarr_seq_str;


public class Subsequence {

    /**
     * LC300. Longest Increasing Subsequence
     * O(n^2) solution, L[j] = max(L[i]) + 1, where i < j && a[i] < a[j]
     * @param nums
     * @return the length of subsequence
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

    /**
     * LC673. Number of Longest Increasing Subsequence
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n], cnt = new int[n];
        int maxLen = 0, res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                if (dp[i] == dp[j] + 1) cnt[i] += cnt[j];
                else if (dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    cnt[i] = cnt[j];
                }
            }
            if (dp[i] == maxLen) res += cnt[i];
            else if (dp[i] > maxLen) {
                maxLen = dp[i];
                res = cnt[i];
            }
        }
        return res;
    }
}
