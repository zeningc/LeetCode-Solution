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
    public int rob(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }
    
    int[] dfs(TreeNode root)    {
        if (root.left == null && root.right == null)
            return new int[] {root.val, 0};
        int[] left = new int[2];
        int[] right = new int[2];
        
        if (root.left != null)
            left = dfs(root.left);

        if (root.right != null)
            right = dfs(root.right);
        
        int notSteal = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        int steal = root.val + left[1] + right[1];
        return new int[] {steal, notSteal};
    }
}