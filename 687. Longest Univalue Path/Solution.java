class Solution {
    int ans = 1;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans - 1;
    }
    
    
    int dfs(TreeNode root)  {
        if (root == null)
            return 0;
        
        int left = 0;
        int right = 0;
        
        int ret = dfs(root.left);
        if (root.left != null && root.left.val == root.val)
            left = ret;
        
        ret = dfs(root.right);
        if (root.right != null && root.right.val == root.val)
            right = ret;
        
        ans = Math.max(ans, 1 + left + right);
        
        return Math.max(left, right) + 1;
    }
}
