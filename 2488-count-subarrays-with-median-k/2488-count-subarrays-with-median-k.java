class Solution {
    public int countSubarrays(int[] nums, int k) {
        int p = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++)
        {    
            if (nums[i] == k)
            {
                p = i;
                break;
            }
        }
        Map<Integer, Integer> m = new HashMap<>();
        int cnt = 0;
        for (int i = p; i < n; i++)
        {
            if (nums[i] < k)
                cnt++;
            int len = i - p;
            m.put(2 * cnt - len, m.getOrDefault(2 * cnt - len, 0) + 1);
        }
        
        int ans = 0;
        cnt = 0;
        for (int i = p; i >= 0; i--)
        {
            int len = p - i;
            if (nums[i] < k)
                cnt++;
            ans += m.getOrDefault(len - 2 * cnt, 0);
            ans += m.getOrDefault(len - 2 * cnt - 1, 0);
        }
        
        return ans;
    }
}


/*
cnt + cnt1 = len1 - cnt1 + len - cnt
OR
cnt + cnt1 + 1 = len1 - cnt1 + len - cnt


2 * cnt - len = len1 - 2 * cnt1 / len1 - 2 * cnt1 - 1

*/