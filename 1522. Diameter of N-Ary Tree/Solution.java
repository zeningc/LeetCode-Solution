class Solution {
    int ans = 0;
    public int diameter(Node root) {
        dfs(root);
        return ans - 1;
    }
    
    int dfs(Node root)   {
        if (root == null)
            return 0;
        int max1 = 0;
        int max2 = 0;
        for (Node ch : root.children)   {
            int len = dfs(ch);
            if (len > max1) {
                max2 = max1;
                max1 = len;
            }
            else if (len > max2)    {
                max2 = len;
            }
        }
        
        ans = Math.max(ans, max1 + max2 + 1);
        
        return max1 + 1;
    }
}
