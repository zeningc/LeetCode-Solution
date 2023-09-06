class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        LinkedList<Integer> dupProfit = new LinkedList<>();
        boolean[] seen = new boolean[items.length + 1];
        int cnt = 0;
        long p = 0L;
        for (int i = 0; i < k; i++) {
            if (!seen[items[i][1]])
                cnt++;
            if (seen[items[i][1]])
                dupProfit.offer(items[i][0]);
            seen[items[i][1]] = true;
            p += items[i][0];
        }
        
        long ans = p + (long)cnt * cnt;
        
        for (int i = k; i < items.length; i++)  {
            if (seen[items[i][1]])
                continue;
            if (dupProfit.isEmpty())
                break;
            cnt++;
            p -= dupProfit.pollLast();
            p += items[i][0];
            seen[items[i][1]] = true;
            ans = Math.max(ans, p + (long)cnt * cnt);
        }
        
        return ans;
    }
}