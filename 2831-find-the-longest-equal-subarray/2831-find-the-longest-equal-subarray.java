class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        int lo = 0;
        int maxFreq = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for (int hi = 0; hi < n; hi++) {
            int num = nums.get(hi);
            m.put(num, m.getOrDefault(num, 0) + 1);
            maxFreq = Math.max(maxFreq, m.get(num));
            if (hi - lo + 1 - maxFreq > k)    {
                m.put(nums.get(lo), m.get(nums.get(lo)) - 1);
                lo++;
            }
        }
        return maxFreq;
    }
}