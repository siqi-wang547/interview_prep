package array;

import java.util.ArrayList;
import java.util.List;

public class MajorityElem {

    /**
     * LC169. Majority Element
     * @Solution based on Moore voting, https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
     * @param nums input array, unsorted
     * @return the majority element that appears more than ⌊ n/2 ⌋ times
     */
    public int majorityElement(int[] nums) {

        int cnt = 0, cand = 0;
        for (int n: nums) {
            if (cnt == 0) {
                cand = n;
                cnt = 1;
            }
            else if (n == cand) cnt++;
            else cnt--;
        }

        cnt = 0;
        for (int n: nums) {
            if (n == cand) cnt++;
        }

        return (cnt > nums.length / 2)? cand : -1;
    }

    /**
     * LC229. Majority Element II
     * @param nums input array, unsorted
     * @return list of elements that appears more than ⌊ n/3 ⌋ times
     */
    public List<Integer> majorityElementII(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int cnt1 = 0, cnt2 = 0, cand1 = 0, cand2 = 1;
        for (int n: nums) {
            if (n == cand1) cnt1++;
            else if (n == cand2) cnt2++;
            else if (cnt1 == 0) {
                cand1 = n;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                cand2 = n;
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0; cnt2 = 0;
        for (int n: nums) {
            if (n == cand1) cnt1++;
            else if (n == cand2) cnt2++;
        }

        if (cnt1 > nums.length / 3) res.add(cand1);
        if (cnt2 > nums.length / 3) res.add(cand2);
        return res;
    }
}
