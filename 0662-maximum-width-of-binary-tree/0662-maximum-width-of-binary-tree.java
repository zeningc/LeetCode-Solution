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
    int ans = 1;
    Map<Integer, Integer> leftMost;
    public int widthOfBinaryTree(TreeNode root) {
        leftMost = new HashMap<>();
        dfs(root, 1, 1);
        return ans;
    }
    
    void dfs(TreeNode node, int idx, int level) {
        if (!leftMost.containsKey(level))
            leftMost.put(level, idx);
        ans = Math.max(ans, idx - leftMost.get(level) + 1);
        if (node.left != null)
            dfs(node.left, idx * 2, level + 1);
        if (node.right != null)
            dfs(node.right, idx * 2 + 1, level + 1);
    }
}