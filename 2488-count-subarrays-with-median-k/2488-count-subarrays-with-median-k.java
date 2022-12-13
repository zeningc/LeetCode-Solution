class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int pivot = -1;
        int pivotRight = -1;
        for (int i = 0; i < n; i++)
            if (nums[i] == k)
                pivot = i;
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = pivot; i < n; i++) {
            int len = i - pivot;
            if (nums[i] < k)
                cnt++;
            map.put(len - 2 * cnt, map.getOrDefault(len - 2 * cnt, 0) + 1);
        }
        int ans = 0;
        cnt = 0;
        for (int i = pivot; i >= 0; i--)    {
            int len = pivot - i;
            if (nums[i] < k)
                cnt++;
            ans += map.getOrDefault(2 * cnt - len, 0);
            ans += map.getOrDefault(2 * cnt - len + 1, 0);
        }
        
        return ans;
    }
}

// cnt len - cnt cnt1 len1 - cnt1

// cnt + cnt1 == len - cnt + len1 - cnt1
// 2cnt - len = len1 - 2cnt1
// ------------------------------------
// cnt + cnt1 + 1 == len - cnt + len1 - cnt1
// 2cnt - len + 1 == len1 - 2cnt1



