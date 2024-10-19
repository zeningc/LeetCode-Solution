class Solution {
    Long[] cache;
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        int[] sArr = numToArr(Long.parseLong(s));
        
        int[] finishArr = numToArr(finish);
        cache = new Long[finishArr.length];
        
        long b = dfs(sArr, finishArr, 0, limit, false, true);
        
        if (start == 1)
            return b;
        
        int[] startArr = numToArr(start - 1);
        cache = new Long[startArr.length];
        
        long a = dfs(sArr, startArr, 0, limit, false, true);
        
        
        
        return b - a;
    }
    
    int[] numToArr(long num)    {
        int cnt = 0;
        long t = num;
        while (t != 0)    {
            cnt++;
            t /= 10;
        }
        
        int[] arr = new int[cnt];
        for (int i = cnt - 1; i >= 0; i--)  {
            arr[i] = (int)(num % 10);
            num /= 10;
        }
        
        return arr;
    }
    long dfs(int[] suffix, int[] num, int idx, int limit, boolean isNum, boolean isLimit) {
        if (suffix.length > num.length)
            return 0;
        if (idx + suffix.length >= num.length)    {
            if (!isNum || isNum && isLimit) {
                if (!isNum && idx != 0)
                    return 1;
                for (int j = idx; j < num.length; j++)
                    if (suffix[j - idx] < num[j])
                        return 1;
                    else if (suffix[j - idx] > num[j])
                        return 0;
                return 1;
            }
            
            return 1;
        }
        
        if (isNum && !isLimit && cache[idx] != null)
            return cache[idx];
        
        long ans = 0;
        if (!isNum) {
            ans += dfs(suffix, num, idx + 1, limit, false, false);
        }
        
        int lo = isNum ? 0 : 1;
        int hi = Math.min(limit, isLimit ? num[idx] : limit);
        
        for (int d = lo; d <= hi; d++)  {
            ans += dfs(suffix, num, idx + 1, limit, true, isLimit && num[idx] == d);
        }
        
        if (isNum && !isLimit)
            cache[idx] = ans;
        
        return ans;
    }
}