package tree;

import java.util.*;

public class CntSmaller {

    class TreeNode {
        int val, sum, dup = 1;
        TreeNode left, right;

        public TreeNode(int v, int s) {
            val = v;
            sum = s;
        }
    }

    private TreeNode insert(int num, TreeNode node, Integer[] ans, int i, int preSum) {
        if (node == null) {
            node = new TreeNode(num, 0);
            ans[i] = preSum;
        } else if (node.val == num) {
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if (node.val > num) {
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else {
            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
        }
        return node;
    }

    /**
     * LC315 Count of Smaller Numbers After Self
     * @Idea: build a BST using node that stores value, the cnt of left subtree and duplicate times
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        TreeNode root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }
}
