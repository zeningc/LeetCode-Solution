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
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty())    {
            TreeNode curr = q.poll();
            TreeNode t = curr.left;
            curr.left = curr.right;
            curr.right = t;
            if (curr.left != null)
                q.offer(curr.left);
            if (curr.right != null)
                q.offer(curr.right);            
        }
        return root;
    }
}
