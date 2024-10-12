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
    public TreeNode[] splitBST(TreeNode root, int target) {
        if (root == null)
            return new TreeNode[2];
        
        if (root.val > target)  {
            TreeNode[] ans = splitBST(root.left, target);
            root.left = ans[1];
            return new TreeNode[] {ans[0], root};
        }
        
        TreeNode[] ans = splitBST(root.right, target);
        root.right = ans[0];
        return new TreeNode[] {root, ans[1]};
    }
}