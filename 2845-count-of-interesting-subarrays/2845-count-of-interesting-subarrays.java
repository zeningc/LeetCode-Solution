class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        Map<Integer, Long> m = new HashMap<>();
        m.put(0, 1L);
        int cnt = 0;
        long ans = 0;
        for (int num : nums)
        {
            if (num % modulo == k)
            {
                cnt++;
            }
            
            ans += m.getOrDefault((cnt + modulo - k) % modulo, 0L);
            m.put(cnt % modulo, m.getOrDefault(cnt % modulo, 0L) + 1);
        }
        
        return ans;
    }
}


// (x + m - y) % m = k

// (x + m - k) % m


