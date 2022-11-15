class Solution {
    public int subarrayLCM(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            Map<Integer, Integer> m1 = new HashMap<>();
            for (Map.Entry<Integer, Integer> e : map.entrySet())    {
                int d = e.getKey();
                int cnt = e.getValue();
                int l = lcm(nums[i], d);
                if (k == l)
                    ans += cnt;
                if (k % l == 0)
                    m1.put(l, m1.getOrDefault(l, 0) + cnt);
            }
            map = m1;
        }
        
        return ans;
    }
    
    int gcd(int m, int n)   {
        if (n > m)
            return gcd(n, m);
        if (m % n == 0)
            return n;
        return gcd(n, m % n);
    }
    
    int lcm(int m, int n)   {
        return m * n / gcd(m, n);
    }
}