class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[][] qs = new int[queries.length][3];
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++)
            qs[i] = queries[i][0] > queries[i][1] ? new int[] {queries[i][1], queries[i][0], i} : new int[] {queries[i][0], queries[i][1], i};
        
        Arrays.sort(qs, (a, b) -> b[1] - a[1]);
        List<int[]> stack = new ArrayList<>();
        int p = heights.length - 1;
        for (int i = 0; i < qs.length; i++) {
            int a = qs[i][0];
            int b = qs[i][1];
            int idx = qs[i][2];
            while (p >= b)    {
                while (!stack.isEmpty() && stack.get(stack.size() - 1)[0] <= heights[p])    {
                    stack.remove(stack.size() - 1);
                }
                stack.add(new int[] {heights[p], p});
                p--;
            }
            
            if (a == b || heights[b] > heights[a]) {
                ans[idx] = b;
                continue;
            }
            
            int lo = 0;
            int hi = stack.size() - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (stack.get(mid)[0] > Math.max(heights[a], heights[b]))
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }
            
            if (hi == -1)
                ans[idx] = -1;
            else
                ans[idx] = stack.get(hi)[1];
            
        }
        
        return ans;
    }
}