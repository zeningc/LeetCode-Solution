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
    public int kthSmallest(TreeNode root, int k) {
        int target = dfs(root) - k + 1;
        TreeNode p1 = root;
        int cnt = 0;
        while (p1 != null)  {
            TreeNode p2 = p1.right;
            if (p2 != null) {
                while (p2.left != null && p2.left != p1)
                    p2 = p2.left;
                
                if (p2.left != p1) {
                    p2.left = p1;
                    p1 = p1.right;
                    continue;
                }
                
                cnt++;
                if (cnt == target)
                    return p1.val;
                p2.left = null;
                p1 = p1.left;
                continue;
            }
            
            cnt++;
            if (cnt == target)
                return p1.val;
            p1 = p1.left;
        }
        
        return -1;
    }
    
    int dfs(TreeNode node)  {
        if (node == null)
            return 0;
        int cnt = 0;
        return dfs(node.left) + dfs(node.right) + 1;
    }
}