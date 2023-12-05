import java.util.NavigableMap;

class Solution {
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        TreeMap<Long, Integer> dp = new TreeMap<>();
        long[] presum = new long[n];
        int[] len = new int[n];

        for (int i = 0; i < n; i++)
            presum[i] = nums[i] + (i == 0 ? 0 : presum[i - 1]);
        for (int i = 0; i < n; i++) {
            int j = dp.floorKey(presum[i]) == null ? -1 : dp.floorEntry(presum[i]).getValue();
            long cur = (j == -1 ? presum[i] : presum[i] - presum[j]) + presum[i];
            len[i] = (j == -1 ? 0 : len[j]) + 1;
            if (dp.ceilingKey(cur) != null) {
                NavigableMap<Long, Integer> subMap = dp.subMap(dp.ceilingKey(cur), true, dp.lastKey(), true);
                Iterator<Map.Entry<Long, Integer>> iterator = subMap.entrySet().iterator();
                while (iterator.hasNext())  {
                    iterator.next();
                    iterator.remove();
                }
            }
            dp.put(cur, i);
        }
        
        return len[n - 1];
    }
}