class Solution {
    List<Integer> ans;
    public List<Integer> preorderTraversal(TreeNode root) {
        ans = new LinkedList<>();
        dfs(root);
        return ans;
    }
    
    void dfs(TreeNode root) {
        if (root == null)
            return;
        ans.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
