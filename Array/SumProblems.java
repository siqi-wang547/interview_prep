package array;
import tree.TreeNode;
import java.util.*;

public class SumProblems {

    /**
     * LC1. Two Sum
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        // if (nums.length == 0) return res;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                res[0] = i;
                res[1] = map.get(nums[i]);
            } else map.put(target-nums[i], i);
        }
        return res;
    }

    /**
     * LC167. Two Sum II - Input array is sorted
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumII(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) return new int[]{i+1, j+1};
            else if (nums[i] + nums[j] < target) i++;
            else j--;
        }
        return new int[2];
    }

    /**
     * LC170. Two Sum III - Data structure design, more add than
     */
    class TwoSum {

        private Map<Integer, Integer> map;

        /** Initialize your data structure here. */
        public TwoSum() {
            map = new HashMap<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for (int num: map.keySet()) {
                int res = value - num;
                if (res == num) {
                    if (map.get(num) > 1) return true;
                } else if (map.containsKey(res)) return true;
            }
            return false;
        }
    }

    /**
     * LC653. Two Sum IV - Input is a BST
     * using dfs, input may be not a BST
     * can also get an array by inorder traversal and then solve by two pointers
     * @param root
     * @param k
     * @return
     */
    public boolean twoSumIV(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    private boolean dfs(TreeNode root, Set<Integer> set, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    /**
     * LC15. Three Sum
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums.length == 0) return new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            int left = i + 1, right  = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                else if (nums[left] + nums[right] < target) left++;
                else right--;
            }
        }
        return new ArrayList<>(res);
    }

    /**
     * LC18. 4Sum
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;
        Arrays.sort(nums);
        int len = nums.length, max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target) return res;
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int lo = j + 1, hi = len - 1;
                while (lo < hi) {
                    int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++; hi--;
                    } else if (sum < target) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> kSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        kSum(result, new ArrayList<Integer>(), nums, target, 4, 0, nums.length-1);
        return result;
    }

    private void kSum(List<List<Integer>> result,List<Integer> cur,int[] nums, int target,int k,int start, int end){
        if(k == 0 || nums.length==0 || start>end) return;
        if(k == 1){
            for (int i = start; i <= end ; i++) {
                if(nums[i] == target){
                    cur.add(nums[i]);
                    result.add(new ArrayList<Integer>(cur));
                    cur.remove(cur.size()-1);
                }
            }
            return;
        }

        if(k == 2){ // 2 sum
            int sum;
            while (start < end){
                sum = nums[start]+nums[end];

                if(sum == target){
                    cur.add(nums[start]);
                    cur.add(nums[end]);
                    result.add(new ArrayList<Integer>(cur));
                    cur.remove(cur.size()-1);
                    cur.remove(cur.size()-1);

                    //avoid duplicate
                    while(start < end && nums[start] == nums[start+1]) ++start;
                    ++start;
                    while(start < end && nums[end] == nums[end-1]) --end;
                    --end;
                }else if(sum < target){
                    //avoid duplicate
                    while(start < end && nums[start] == nums[start+1]) ++start;
                    ++start;
                }else{
                    while(start < end && nums[end] == nums[end-1]) --end;
                    --end;
                }
            }
            return;
        }

        //避免一些不必要case
        if(k*nums[start] > target || k*nums[end]<target) return;

        // k > 2 : choose nums[i] and do k-1 sum on the rest at right
        for (int i = start; i <= (end-k+1) ; i++) {
            // avoid duplicate
            if(i>start && nums[i]==nums[i-1]) continue;
            // 重点
            if(k*nums[i] <= target) { //避免掉一些不必要case
                cur.add(nums[i]);
                kSum(result, cur, nums, target - nums[i], k - 1, i + 1, end);
                cur.remove(cur.size() - 1);
            }
        }

    }
}
