class Solution {
    int path = 0;
    public int sumNumbers(TreeNode root) {  
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)    {
            return path * 10 + root.val;
        }
        int ans = 0;
        path = path * 10 + root.val;
        ans += sumNumbers(root.left);
        ans += sumNumbers(root.right);
        path /= 10;
        return ans;
    }
    
}
