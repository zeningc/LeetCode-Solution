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
    int ans = -1;
    int p,q;
    public int findDistance(TreeNode root, int p, int q) {
        this.p = p;
        this.q = q;
        if (p == q)
            return 0;
        dfs(root);
        return ans;
    }
    
    int dfs(TreeNode root)  {
        if (root == null || ans != -1)
            return -1;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left != -1 && right != -1)  {
            ans = left + right;
            return -1;
        }
        if (left != -1) {
            if (root.val == p || root.val == q) {
                ans = left;
                return -1;
            }
            return left + 1;
        }
        
        if (right != -1) {
            if (root.val == p || root.val == q) {
                ans = right;
                return -1;
            }
            return right + 1;
        }
        
        if (root.val == p || root.val == q)
            return 1;
        return -1;
    }
}