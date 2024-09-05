class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        boolean[] pre = new boolean[n];
        boolean[] post = new boolean[n];
        pre[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]){
                pre[i] = true;
                continue;
            }
            break;
        }
        post[n - 1] = true;
        for (int i = n - 2; i >= 0; i--)    {
            if (nums[i] < nums[i + 1])  {
                post[i] = true;
                continue;
            }
            break;
        }
        
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (post[i])
                list.add(new int[] {i, nums[i]});
        }
        long ans = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0 && !pre[i - 1])
                break;
            int target = i == 0 ? Integer.MIN_VALUE : nums[i - 1];
            while (idx < list.size() && list.get(idx)[0] <= i)
                idx++;
            int lo = idx;
            int hi = list.size() - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (list.get(mid)[1] > target)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            int curEnd = lo == list.size() ? n : list.get(lo)[0];
            ans += n - curEnd + 1;
        }
        
        return ans;
    }
}