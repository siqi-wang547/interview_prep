package array;

import java.util.Arrays;

public class QuickSelect {

    public static int findKthLargest(int[] nums, int k) {

        return quickSelect(nums, k-1, 0, nums.length-1);
    }

    private static int quickSelect(int[] nums, int k, int left, int right) {
        int mid = left + (right - left) / 2, pivot = nums[mid];
        int l = left, r = right;
        System.out.printf("mid=%d, l=%d, r=%d, pivot=%d, %s\n", mid, l, r, pivot, Arrays.toString(nums));
        while (left <= right) {
            while (nums[left] > pivot) left++;
            while (nums[right] < pivot) right--;
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
//        System.out.println("After swap: " + Arrays.toString(nums));
        System.out.printf("left=%d, right=%d, k=%d\n", left, right, k);
        if (l < right && k <= right) return quickSelect(nums, k, l, right);
        if (left < r && k >= left) return quickSelect(nums, k, left, r);
        System.out.println(Arrays.toString(nums));
        return nums[k];
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] input = new int[] {10,9,2,3,7,4,8,1,6};
        System.out.println(findKthLargest(input, 6));
    }
}
