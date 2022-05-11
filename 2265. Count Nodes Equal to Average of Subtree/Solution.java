class Solution {
    int ans = 0;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }
    
    int[] dfs(TreeNode root)   {
        if (root == null)
            return new int[] {0, 0};
        
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        
        
        int leftCnt = left[0];
        int rightCnt = right[0];
        int leftSum = left[1];
        int rightSum = right[1];
        
        if ((leftSum + rightSum + root.val) / (leftCnt + rightCnt + 1) == root.val)   {
            ans++;
        }
            
        
        return new int[] {leftCnt + rightCnt + 1, leftSum + rightSum + root.val};
    }
}
