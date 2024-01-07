class Solution {
    public long countExcellentPairs(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums)
            if (!m.containsKey(num))
                m.put(num, cnt1(num));
        
        List<Integer> list = new ArrayList<>(m.keySet());
        Collections.sort(list, (a, b) -> m.get(a) - m.get(b));
        
        long ans = 0;
        int q = list.size() - 1;
        for (int p = 0; p < list.size(); p++) {
            int a = m.get(list.get(p));
            while (q >= 0 && a + m.get(list.get(q)) >= k)
                q--;
            ans += q >= p ? (list.size() - q - 1) : (list.size() - p - 1);
        }
        ans *= 2;
        for (int num : list)
            if (2 * m.get(num) >= k)
                ans++;
        return ans;
    }
    
    int cnt1(int n) {
        int cnt = 0;
        while (n != 0)  {
            n &= (n - 1);
            cnt++;
        }
        return cnt;
    }
}