class Solution {
    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums)
            m.put(num, m.getOrDefault(num, 0) + 1);
        int ans = 0;
        int lo = 0;
        int hi = nums.length;
        for (Map.Entry<Integer, Integer> e : m.entrySet())  {
            int cnt = e.getValue();
            hi -= cnt;
            ans += lo * cnt * hi;
            lo += cnt;
        }
        return ans;
    }
}