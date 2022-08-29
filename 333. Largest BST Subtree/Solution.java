class Solution {
    public int largestBSTSubtree(TreeNode root) {
        return dfs(root)[2];
    }
    
    int[] dfs(TreeNode root)    {
        if (root == null)
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        
        if (root.val > left[1] && root.val < right[0])  {
            return new int[] {Math.min(root.val, left[0]), Math.max(root.val, right[1]), left[2] + right[2] + 1};
        }
        
        return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])};
    }
}
