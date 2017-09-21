package slidingwindow;
import java.util.*;
import java.util.function.*;

/**
 * Created by siqi on 9/17/17.
 */
public class SlidingWindowMedian {
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
