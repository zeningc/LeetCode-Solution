class Solution {
    List<Integer> list;
    public boolean isValidBST(TreeNode root) {
        list = new LinkedList<>();
        dfs(root);
        long prev = Long.MIN_VALUE;
        for (int n : list)  {
            if (prev >= n)
                return false;
            prev = n;
        }
        
        return true;
    }
    
    void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}