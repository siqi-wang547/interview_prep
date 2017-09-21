package subarr_seq_str;
import java.util.*;

public class MaxLenSubarrSumLessEqualK {

    /**
     * Question from: https://stackoverflow.com/questions/39084147/largest-sum-of-contiguous-subarray-no-larger-than-k
     * @param a input array, can have negative values
     * @param k input target number K
     * @return max sum
     */
    public int maxSumSubArray(int[] a , int k) {

        int max = Integer.MIN_VALUE;
        int sumj = 0;
        TreeSet<Integer> s = new TreeSet();
        s.add(0);

        for(int i=0;i<a.length;i++){
            int t = sumj + a[i];
            sumj = t;
            Integer gap = s.ceiling(sumj - k);
            if(gap != null) max = Math.max(max, sumj - gap);
            s.add(t);
        }

        return max;
    }
}
