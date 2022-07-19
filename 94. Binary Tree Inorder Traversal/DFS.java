class Solution {
    List<Integer> ans;
    public List<Integer> inorderTraversal(TreeNode root) {
        ans = new LinkedList<>();
        dfs(root);
        return ans;
    }
    
    void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }
}
