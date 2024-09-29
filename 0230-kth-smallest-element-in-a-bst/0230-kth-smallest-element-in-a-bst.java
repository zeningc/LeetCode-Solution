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
        TreeNode p1 = root;
        while (p1 != null)  {
            TreeNode p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1)
                    p2 = p2.right;
                
                if (p2.right != p1) {
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                }
                
                p2.right = null;
                k--;
                if (k == 0)
                    return p1.val;
                p1 = p1.right;
                continue;
            }
            k--;
            if (k == 0)
                return p1.val;
            p1 = p1.right;
        }
        
        return -1;
    }
}