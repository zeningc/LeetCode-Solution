class Solution {
    public int[] colorTheArray(int n, int[][] queries) {
        int[] colors = new int[n];
        int[] ans = new int[queries.length];
        int cnt = 0;
        for (int i = 0; i < queries.length; i++)    {
            int[] query = queries[i];
            int idx = query[0];
            int color = query[1];
            if (color == colors[idx])   {
                ans[i] = cnt;
                continue;
            }
            
            if (idx > 0 && colors[idx] != 0 && colors[idx] == colors[idx - 1])
                cnt--;
            if (idx < n - 1 && colors[idx + 1] != 0 && colors[idx] == colors[idx + 1])
                cnt--;
            
            if (idx > 0 && color == colors[idx - 1])
                cnt++;
            if (idx < n - 1 && color == colors[idx + 1])
                cnt++;
            
            colors[idx] = color;
            ans[i] = cnt;
        }
        return ans;
    }
}