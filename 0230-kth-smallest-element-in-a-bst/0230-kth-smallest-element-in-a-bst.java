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
        int cnt = countNode(root);
        k = cnt - k + 1;
        TreeNode p1 = root;
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
                
                p2.left = null;
                k--;
                if (k == 0)
                    return p1.val;
                p1 = p1.left;
                continue;
            }
            k--;
            if (k == 0)
                return p1.val;
            p1 = p1.left;
        }
        
        return -1;
    }
    
    int countNode(TreeNode node)    {
        if (node == null)
            return 0;
        return countNode(node.left) + countNode(node.right) + 1;
    }
}