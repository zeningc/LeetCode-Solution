class Solution {
    boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return ans;
    }
    
    int dfs(TreeNode root)  {
        if (root == null)
            return 0;
        if (!ans)
            return 0;
        
        int left = dfs(root.left);
        int right = dfs(root.right);
        
        if (Math.abs(left - right) > 1)
            ans = false;
        
        return Math.max(left, right) + 1;
    }
}
