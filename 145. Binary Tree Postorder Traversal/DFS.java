class Solution {
    List<Integer> ans;
    public List<Integer> postorderTraversal(TreeNode root) {
        ans = new LinkedList<>();
        dfs(root);
        return ans;
    }
    
    void dfs(TreeNode root)  {
        if (root == null)
            return;
        dfs(root.left);
        dfs(root.right);
        ans.add(root.val);
    }
}
