class Solution {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        int[] finishArr = numToArr(finish);
        int[] startArr = numToArr(start - 1);
        long a = dfs(new Long[finishArr.length], s, finishArr, 0, limit, true);
        long b = dfs(new Long[startArr.length], s, startArr, 0, limit, true);
        return a - b;
    }
    
    long dfs(Long[] mem, String s, int[] nums, int idx, int limit, boolean isLimit) {
        if (nums.length < s.length())
            return 0;
        if (idx >= nums.length - s.length())    {
            if (!isLimit)
                return 1;
            for (int i = nums.length - s.length(), j = 0; i < nums.length; i++, j++)    {
                if (nums[i] > s.charAt(j) - '0')
                    return 1;
                if (nums[i] < s.charAt(j) - '0')
                    return 0;
            }
            return 1;
        }
        if (!isLimit && mem[idx] != null)
            return mem[idx];
        
        long ans = 0;
        
        int lo = 0;
        int hi = Math.min(limit, isLimit ? nums[idx] : 9);
        
        for (int i = lo; i <= hi; i++)  {
            ans += dfs(mem, s, nums, idx + 1, limit, isLimit && nums[idx] == i);
        }
        if (!isLimit)
            mem[idx] = ans;
        return ans;
    }
    
    int[] numToArr(long x)  {
        if (x == 0)
            return new int[] {0};
        int cnt = 0;
        long t = x;
        while (t != 0)  {
            t /= 10;
            cnt++;
        }
        t = x;
        int[] arr = new int[cnt];
        for (int i = cnt - 1; i >= 0; i--)  {
            arr[i] = (int)(t % 10);
            t /= 10;
        }
        
        return arr;
    }
}