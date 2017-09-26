package tree;
public class SegmentTrees {

    /**
    * LeetCode307. Range Sum Query - mutable
    * Solution: build a binary segment tree to achieve O(logn) for both update and sum query
    * Trick: in sumRange, use a mid of root to keep track of the split point can avoid null pointer than directly go left or right
    */
    
    class TreeNode {
        int val;
        TreeNode left, right;
        int start, end;
        // public TreeNode(int val) { this.val = val; } 
        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            val = 0;
        }
    }
    
    private TreeNode root;
    private int[] arr;
    
    private TreeNode buildTree(int[] nums, int start, int end) {
        if (end < start) return null;
        TreeNode node = new TreeNode(start, end);
        if (start == end) node.val = nums[start];
        else {
            int mid = start + (end - start) / 2;
            node.left = buildTree(nums, start, mid);
            node.right = buildTree(nums, mid+1, end);
            node.val = node.left.val + node.right.val;
        }
        // System.out.println(node.val);
        return node;
    }
    

    public SegmentTrees(int[] nums) {
        arr = nums;
        root = buildTree(nums, 0, nums.length - 1);
    }
    
    public void update(int i, int val) {
        int dif = val - arr[i];
        arr[i] = val;
        TreeNode node = root;
        node.val += dif;
        while (node.left != null || node.right != null) {
            if (node.left != null && i <= node.left.end) {
                node = node.left;
                node.val += dif;
            }
            if (node.right != null && i >= node.right.start) {
                node = node.right;
                node.val += dif;
            }
        }
    }
    
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    
    private int sumRange(TreeNode root, int i, int j) {
        if (root.start == i && j == root.end) {
            // System.out.printf("%d, %d, %d\n", i, j, root.val);
            return root.val;
        }
        int mid = root.start + (root.end - root.start) / 2;
        // System.out.printf("root.val=%d, start=%d, end=%d, mid=%d\n", root.val, root.start, root.end, mid);
        if (j <= mid) return sumRange(root.left, i, j);
        else if (i > mid) return sumRange(root.right, i, j);
        else return sumRange(root.left, i, mid) + sumRange(root.right, mid+1, j);
    }
}
