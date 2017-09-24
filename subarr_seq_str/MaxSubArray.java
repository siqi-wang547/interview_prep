package subarr_seq_str;

public class MaxSubArray {

    /**
     * LC53. Maximum Subarray
     * @param nums input array
     * @return max subarray sum
     */
    public int maxSubArray(int[] nums) {

//      maxSubArray(int A[], int i), which means the maxSubArray for A[0:i ] which must has A[i] as the end element
        if (nums.length == 0) return 0;
        int max_i = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max_i = Math.max(max_i + nums[i], nums[i]);
            max = Math.max(max, max_i);
        }
        return max;
    }

    /**
     * LC152. Maximum Product Subarray
     * @param nums input array
     * @return max subarray product
     */
    public int maxProduct(int[] nums) {
        int r = nums[0]; // for the max result so far

        int max = r, min = r;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) { // when element is negative, swap max and min
                int tmp = max;
                max = min;
                min = tmp;
            }

            // update the max and min because they are either the current element or the subarray product before it
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);

            r = Math.max(r, max);
        }
        return r;
    }
}
