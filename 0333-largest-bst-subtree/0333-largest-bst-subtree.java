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
    public int largestBSTSubtree(TreeNode root) {
        return dfs(root)[2];
    }
    
    int[] dfs(TreeNode root)    {
        if (root == null)
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        
        if (left[0] != Integer.MIN_VALUE && right[0] != Integer.MIN_VALUE && root.val > left[1] && root.val < right[0])
            return new int[] {Math.min(left[0], root.val), Math.max(right[1], root.val), left[2] + right[2] + 1};
        return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])};
    }
    
    
    
}

