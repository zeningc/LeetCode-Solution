class Solution {
    public int minChanges(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length / 2; i++)   {
            int a = nums[i];
            int b = nums[nums.length - i - 1];
            m.put(0, m.getOrDefault(0, 0) + 1);
            m.put(Math.max(Math.max(a, k - a), Math.max(b, k - b)) + 1, m.getOrDefault(Math.max(Math.max(a, k - a), Math.max(b, k - b)) + 1, 0) - 1);
            m.put(Math.max(Math.max(a, k - a), Math.max(b, k - b)) + 1, m.getOrDefault(Math.max(Math.max(a, k - a), Math.max(b, k - b)) + 1, 0) + 2);
            m.put(k + 1, m.getOrDefault(k + 1, 0) - 2);
            m.put(Math.abs(a - b), m.getOrDefault(Math.abs(a - b), 0) - 1);
            m.put(Math.abs(a - b) + 1, m.getOrDefault(Math.abs(a - b) + 1, 0) + 1);
        }
        int ans = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 0; i <= k; i++)    {
            cnt += m.getOrDefault(i, 0);
            ans = Math.min(ans, cnt);
        }
        
        return ans;
    }
}

/*
0 1

max(max(a, k - a), max(b, k - b)) + 1





abs(a - b)



*/