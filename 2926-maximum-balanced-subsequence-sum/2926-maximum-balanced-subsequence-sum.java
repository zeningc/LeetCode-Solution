import java.util.NavigableMap;

class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        TreeMap<Integer, Long> dp = new TreeMap<>();
        int n = nums.length;
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int target = nums[i] - i;
            Map.Entry<Integer, Long> entry = dp.floorEntry(target);
            long cur = nums[i] + Math.max(0, (entry == null ? 0 : entry.getValue()));
            ans = Math.max(ans, cur);
            if (dp.ceilingKey(target) != null)  {
                NavigableMap<Integer, Long> subMap = dp.subMap(dp.ceilingKey(target), true, dp.lastKey(), true);
                Iterator<Map.Entry<Integer, Long>> iterator = subMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Long> subEntry = iterator.next();
                    if (subEntry.getValue() < cur) {
                        iterator.remove();
                        continue;
                    }
                    break;
                }
            }
            
            
            dp.put(target, Math.max(cur, dp.getOrDefault(target, Long.MIN_VALUE)));
        }
                                 
        return ans;
    }
}