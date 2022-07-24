class Solution {
    int k;
    int ans = -1;
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }
    
    void dfs(TreeNode root) {
        if (root == null || ans != -1)
            return;
        dfs(root.left);
        if (k == 1) {
            ans = root.val;
            k--;
            return;
        }
        k--;
        dfs(root.right);
    }
}