package tree;

import java.util.*;

public class Traversal {
    /**
     * Level order traversal using queue
     * @param root
     * @return
     */
    public List<List<Integer>> levelorder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = q.size(); // need to be careful here
            for (int i = 0; i < size; i++) {
                TreeNode peek = q.poll();
                if (peek.left != null) q.add(peek.left);
                if (peek.right != null) q.add(peek.right);
                list.add(peek.val);
            }
            res.add(list);
        }
        return res;
    }

    /**
     * Preorder traversal recursive solution
     * @param root
     * @return
     */
    public List<Integer> preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.add(root.val);
        res.addAll(preorder(root.left));
        res.addAll(preorder(root.right));
        return res;
    }

    /**
     * Preorder traversal using stack
     * @param root
     * @return
     */
    public List<Integer> preorderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode cur = st.pop();
            res.add(cur.val);
            if (cur.right != null) st.push(cur.right);
            if (cur.left != null) st.push(cur.left);
        }
        return res;
    }

    /**
     * Inorder traversal recursive solution
     * @param root
     * @return
     */
    public List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.addAll(inorder(root.left));
        res.add(root.val);
        res.addAll(inorder(root.right));
        return res;
    }

    /**
     * Inorder traversal interative solution using stack
     * @param root
     * @return
     */
    public List<Integer> inorderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !st.isEmpty()) {
            while (cur != null) {
                st.push(cur);
                cur = cur.left;
            }
            cur = st.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    /**
     * Postorder traversal recursive solution
     * @param root
     * @return
     */
    public List<Integer> postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.addAll(postorder(root.left));
        res.addAll(postorder(root.right));
        res.add(root.val);
        return res;
    }

    public List<Integer> postorderIterative(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode cur = st.pop();
            if (cur.left != null) st.push(cur.left);
            if (cur.right != null) st.push(cur.right);
            res.add(0, cur.val);
        }
        return res;
    }

    /**
     * Morris traversal using O(1) space
     * Steps: Node cur starting from root, repeat following
     *  1. if cur doesn't have left child, print cur and then move cur to its right child;
     *  2. if cur has left child, find its inorder predecessor in its left subtree
     *     1) if predecessor has no right child, make cur its right child (threading),
     *     2) if predecessor has right child, make right child null (recover tree structure),
     *          print cur and move cur to its right child
     * @param root
     * @return
     */
    public List<Integer> inorderMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        while (root != null) {
            if (root.left != null) {
                TreeNode tmp = root.left;
                while (tmp.right != null && tmp.right != root)
                    tmp = tmp.right;
                if (tmp.right != null) {
                    tmp.right = null;
                    res.add(root.val);
                    root = root.right;
                } else {
                    tmp.right = root;
                    root = root.left;
                }
            } else {
                res.add(root.val);
                root = root.right;
            }
        }

        return res;
    }
}
