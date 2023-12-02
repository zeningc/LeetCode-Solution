import java.util.NavigableMap;

class Solution {
    public int maxProfit(int[] prices, int[] profits) {
        TreeMap<Integer, Integer> dp1 = new TreeMap<>();
        TreeMap<Integer, Integer> dp2 = new TreeMap<>();
        int n = prices.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp1.ceilingKey(prices[i]) != null)  {
                NavigableMap<Integer, Integer> subMap = dp1.subMap(dp1.ceilingKey(prices[i]), true, dp1.lastKey(), true);
                Iterator<Map.Entry<Integer, Integer>> iterator = subMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> subEntry = iterator.next();
                    if (subEntry.getValue() < profits[i]) {
                        iterator.remove();
                        continue;
                    }
                    break;
                }
            }
            
            dp1.put(prices[i], Math.max(profits[i], dp1.floorKey(prices[i]) == null ? profits[i] : dp1.floorEntry(prices[i]).getValue()));
            
            if (dp1.lowerKey(prices[i]) == null)
                continue;

            if (dp2.lowerKey(prices[i]) != null) {
                ans = Math.max(ans, profits[i] + dp2.lowerEntry(prices[i]).getValue());
            }

            int cur = profits[i] + dp1.lowerEntry(prices[i]).getValue();
            if (dp2.ceilingKey(prices[i]) != null)  {
                NavigableMap<Integer, Integer> subMap = dp2.subMap(dp2.ceilingKey(prices[i]), true, dp2.lastKey(), true);
                Iterator<Map.Entry<Integer, Integer>> iterator = subMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> subEntry = iterator.next();
                    if (subEntry.getValue() < cur) {
                        iterator.remove();
                        continue;
                    }
                    break;
                }
            }
            
            dp2.put(prices[i], Math.max(cur, dp2.floorKey(prices[i]) == null ? cur : dp2.floorEntry(prices[i]).getValue()));
        }
        
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}