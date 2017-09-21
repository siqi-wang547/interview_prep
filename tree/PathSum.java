package tree;

import java.util.*;

public class PathSum {
    /**
     * LC112. Path Sum
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        else return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


    /**
     * LC113. Path Sum II
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(res, root, sum, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode root, int sum, List<Integer> list, int path) {
        list.add(root.val);
        if (root.left == null && root.right == null) {
            if (path + root.val == sum) res.add(new ArrayList<>(list));
            return;
        }

        if (root.left != null) {
            dfs(res, root.left, sum, list, path + root.val);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            dfs(res, root.right, sum, list, path + root.val);
            list.remove(list.size() - 1);
        }
    }

    /**
     * LC437. Path Sum III, intuitive solution
     * @param root
     * @param sum
     * @return
     * Time complexity O(n^2), O(nlgn) if a balanced binary tree
     */
    public int pathSumIII(TreeNode root, int sum) {
        if (root == null) return 0;
        return findPath(root, sum) + pathSumIII(root.left, sum) + pathSumIII(root.right, sum);
    }

    private int findPath(TreeNode root, int sum) {
        int res = 0;
        if (root == null) return res;
        if (root.val == sum) res++;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

    /**
     * LC437. Path Sum III, O(n)r solution
     * @param root
     * @param sum
     * @return
     */
    public int pathSumIII2(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  //Default sum = 0 has one count
        return backtrack(root, 0, sum, map);
    }
    //BackTrack one pass
    public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map){
        if(root == null)
            return 0;
        sum += root.val;
        int res = map.getOrDefault(sum - target, 0);    //See if there is a subarray sum equals to target
        map.put(sum, map.getOrDefault(sum, 0)+1);
        //Extend to left and right child
        res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
        map.put(sum, map.get(sum)-1);   //Remove the current node so it wont affect other path
        return res;
    }

    /**
     * LC124. Binary Tree Maximum Path Sum
     * trick: in recursion the return value is only a downward maximum while using an array to record the global maximum
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        /**
         * use an array (reference type) to pass as parameter in recursion
         * cannot be Integer or int here, because the return value of recursion method is not assigned to the original
         * variable, thus the original variable value would not change
         */
        int[] maxValue = new int[]{Integer.MIN_VALUE};
        maxPathDown(root, maxValue);
        return maxValue[0];
    }

    private int maxPathDown(TreeNode root, int[] maxValue) {
        if (root == null) return 0;
        int left = Math.max(0, maxPathDown(root.left, maxValue));
        int right = Math.max(0, maxPathDown(root.right, maxValue));
        maxValue[0] = Math.max(maxValue[0], root.val + left + right);
        return Math.max(left, right) + root.val;
    }
}
