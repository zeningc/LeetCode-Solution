/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode root;
    TreeNode subRoot;
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        this.root = root;
        this.subRoot = subRoot;
        return dfs(root, subRoot);
    }
    
    boolean dfs(TreeNode root, TreeNode subRoot)    {
        if (root == null && subRoot == null)
            return true;
        if (root == null)
            return false;
        if (subRoot == null)
            return false;
        if (root.val == subRoot.val)    {
            if (dfs(root.left, subRoot.left) && dfs(root.right, subRoot.right))
                return true;
        }
        if (subRoot == this.subRoot)
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        return false;
    }
}