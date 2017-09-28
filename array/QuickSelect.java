package array;

public class QuickSelect {

    public static int findKthLargest(int[] nums, int k) {

        return quickSelect(nums, k-1, 0, nums.length-1);
    }

    private static int quickSelect(int[] nums, int k, int left, int right) {
        int pivot = nums[left + (right - left) / 2];
        int l = left, r = right;
        while (left <= right) {
            while (nums[left] > pivot) left++;
            while (nums[right] < pivot) right--;
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        if (l < right && k <= right) return quickSelect(nums, k, l, right);
        if (left < r && k >= left) return quickSelect(nums, k, left, r);
        return nums[k];
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
