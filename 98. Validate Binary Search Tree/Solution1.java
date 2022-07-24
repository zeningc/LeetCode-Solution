class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    boolean dfs(TreeNode root, long min, long max)  {
        if (root == null)
            return true;
        if (root.val <= min || root.val >= max)
            return false;
        
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
        
    }
}
