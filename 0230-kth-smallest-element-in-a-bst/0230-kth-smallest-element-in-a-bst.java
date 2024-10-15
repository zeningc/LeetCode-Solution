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
        int cnt = 0;
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
                
                cnt++;
                if (cnt == k)
                    return p1.val;
                p2.right = null;
                p1 = p1.right;
                continue;
            }
            
            cnt++;
            if (cnt == k)
                return p1.val;
            p1 = p1.right;
        }
        
        return -1;
    }
}