class Solution {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }
    
    int dfs(TreeNode root)  {
        if (root == null)
            return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        
        ans = Math.max(ans, Math.max(0, left) + Math.max(0, right) + root.val);
        
        return Math.max(Math.max(left, right), 0) + root.val;
    }
}
