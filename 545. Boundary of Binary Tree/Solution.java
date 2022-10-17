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
    List<Integer> ans;
    int idx;
    TreeNode root;
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        this.root = root;
        ans = new LinkedList<>();
        if (root == null)
            return ans;
        ans.add(root.val);
        if (root.left != null){
            dfs(root.left);
        }
        dfs1(root);
        idx = ans.size();
        if (root.right != null)
            dfs2(root.right);
        
        return ans;
    }
    
    void dfs(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return;
        ans.add(root.val);
        if (root.left != null){
            dfs(root.left);
        }
        else if (root.right != null)  {
            dfs(root.right);
        }
    }
    
    void dfs1(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null && root != this.root)
            ans.add(root.val);
        dfs1(root.left);
        dfs1(root.right);
    }
    
    void dfs2(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return;
        ans.add(idx, root.val);
        if (root.right != null){
            dfs2(root.right);
        }
        else if (root.left != null)  {
            dfs2(root.left);
        }
    }
}
