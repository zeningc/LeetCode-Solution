class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++)
            presum[i + 1] = presum[i] + nums[i];
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i <= n; i++)    {
            int sum = presum[i];
            int mod = sum % k;
            ans += map.getOrDefault(mod, 0);
            if (mod < 0)
                ans += map.getOrDefault(mod + k, 0);
            else
                ans += map.getOrDefault(mod - k, 0);
            
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return ans;
    }
}
