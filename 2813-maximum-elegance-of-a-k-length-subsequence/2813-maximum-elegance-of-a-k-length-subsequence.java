class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Map<Integer, Integer> map = new HashMap<>();
        long profit = 0;
        long ans = 0;
        for (int i = 0; i < k; i++) {
            pq.offer(items[i]);
            map.put(items[i][1], map.getOrDefault(items[i][1], 0) + 1);
            ans += items[i][0];
        }
        profit = ans;
        ans += (long)map.size() * map.size();
        for (int i = k; i < items.length; i++)  {
            if (map.containsKey(items[i][1]))
                continue;
            while (!pq.isEmpty())   {
                int[] poll = pq.poll();
                int c = poll[1];
                int val = poll[0];
                if (map.getOrDefault(c, 0) <= 1)
                    continue;
                profit -= val;
                map.put(c, map.getOrDefault(c, 0) - 1);
                profit += items[i][0];
                map.put(items[i][1], map.getOrDefault(items[i][1], 0) + 1);
                pq.offer(items[i]);
                break;
            }
            
            ans = Math.max(profit + (long)map.size() * map.size(), ans);
                
        }
        
        return ans;
    }
}