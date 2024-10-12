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
        TreeNode dummySm = new TreeNode(-1);
        TreeNode dummyLg = new TreeNode(-1);
        TreeNode sm = dummySm;
        TreeNode lg = dummyLg;
        TreeNode node = root;
        while (node != null)    {
            TreeNode nxt;
            if (node.val <= target) {
                nxt = node.right;
                sm.right = node;
                node.right = null;
                sm = sm.right;
            }
            else    {
                nxt = node.left;
                lg.left = node;
                node.left = null;
                lg = lg.left;
            }
            node = nxt;
        }
        
        return new TreeNode[] {dummySm.right, dummyLg.left};
    }
}