package slidingwindow;
import java.util.*;
/**
 * Created by siqi on 9/17/17.
 */
public class SlidingWindowMax {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums.length == 0 || k == 1) return nums;
    int n = nums.length;
    int[] res = new int[n - k + 1];
    int idx = 0; // index for update result array
    Deque<Integer> dq = new ArrayDeque<>();
    // deque to store the max index from the original array in each window
    for (int i = 0; i < nums.length; i++) {
      while (!dq.isEmpty() && dq.peek() < i - k + 1) dq.poll();
      while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
      dq.offer(i);
      if (i >= k - 1) res[idx++] = nums[dq.peek()];
    }
    return res;
  }}
