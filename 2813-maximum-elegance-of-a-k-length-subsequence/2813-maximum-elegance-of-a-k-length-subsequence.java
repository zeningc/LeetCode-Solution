class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> items[a][0] - items[b][0]);
        long ans = 0;
        for (int i = 0; i < k; i++) {
            pq.offer(i);
            freq.put(items[i][1], freq.getOrDefault(items[i][1], 0) + 1);
            ans += items[i][0];
        }
        long profit = ans;
        ans += (long)freq.size() * freq.size();
        
        for (int i = k; i < items.length; i++)  {
            boolean findReplace = false;
            if (freq.containsKey(items[i][1]))
                continue;
            while (!pq.isEmpty())   {
                int j = pq.poll();
                if (freq.get(items[j][1]) == 1)
                    continue;
                freq.put(items[j][1], freq.get(items[j][1]) - 1);
                findReplace = true;
                profit -= items[j][0];
                break;
            }
            if (!findReplace)
                break;
            pq.offer(i);
            profit += items[i][0];
            freq.put(items[i][1], freq.getOrDefault(items[i][1], 0) + 1);
            ans = Math.max(ans, profit + (long)freq.size() * freq.size());
        }
        
        return ans;
    }
}