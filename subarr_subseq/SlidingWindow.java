package subarr_subseq;

import java.util.*;
import java.util.function.Supplier;

public class SlidingWindow {

    /**
     * LC239. Sliding Window Maximum
     * @idea use a double-ended queue to store the index of current window
     *       from right to left (current tail to head of queue, 'i' not enqueued yet)
     *       elements enqueued before and smaller than the current element should be removed
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 1) return nums;
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int idx = 0; // index for update result array
        Deque<Integer> dq = new ArrayDeque<>();
        // deque to store the max index from the original array in each window
        for (int i = 0; i < nums.length; i++) {
            if (!dq.isEmpty() && dq.peek() < i - k + 1) dq.poll();
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            dq.offer(i);
            if (i >= k - 1) res[idx++] = nums[dq.peek()];
        }
        return res;
    }

    /**
     * LC480. Sliding Window Median
     * @param nums
     * @param k
     * @return
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> comp = (a, b) -> (nums[a] == nums[b]) ?
                Integer.compare(a, b) : Integer.compare(nums[a], nums[b]);

        TreeSet<Integer> bigger = new TreeSet<>(comp);
        TreeSet<Integer> smaller = new TreeSet<>(comp.reversed());
        double[] medians = new double[nums.length - k + 1];

        Runnable balance = () -> {
            while (smaller.size() > bigger.size())
                bigger.add(smaller.pollFirst());
        };

        Supplier<Double> median = (k % 2 == 0 ?
                () -> (double) nums[bigger.first()] + nums[smaller.first()] / 2 :
                () -> (double) nums[bigger.first()]);

        for (int i = 0; i < k; i++) bigger.add(i);
        balance.run();
        medians[0] = median.get();

        for (int i = k; i < nums.length; i++) {
            if (!smaller.remove(i - k)) bigger.remove(i - k);
            bigger.add(i);
            smaller.add(bigger.pollFirst());
            balance.run();
            medians[i - k + 1] = median.get();
        }

            return medians;
    }

    public static void main(String[] args) {
        int[] ip = new int[]{-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        System.out.println(Arrays.toString(medianSlidingWindow(ip, 3)));
    }

    /**
     * [-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648]
     3
     */
}
