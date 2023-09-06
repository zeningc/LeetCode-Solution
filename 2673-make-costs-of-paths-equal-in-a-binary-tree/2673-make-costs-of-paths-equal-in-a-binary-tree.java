class Solution {
    int n;
    int[] cost;
    int ans;
    public int minIncrements(int n, int[] cost) {
        this.n = n;
        this.cost = cost;
        ans = 0;
        dfs(1);
        return ans;
    }
    
    
    int dfs(int node) {
        if (node > n)
            return 0;
        int left = dfs(node * 2);
        int right = dfs(node * 2 + 1);
        int diff = Math.abs(left - right);
        ans += diff;
        return Math.max(left, right) + cost[node - 1];
    }
}