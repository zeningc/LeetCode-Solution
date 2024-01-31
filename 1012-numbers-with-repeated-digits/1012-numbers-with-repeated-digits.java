class Solution {
    Integer[][][][] mem;
    int[] nums;
    int cnt;
    public int numDupDigitsAtMostN(int n) {
        cnt = 0;
        int t = n;
        while (t != 0)  {
            t /= 10;
            cnt++;
        }
        t = n;
        mem = new Integer[cnt][1 << 10][2][2];
        nums = new int[cnt];
        for (int i = cnt - 1; i >= 0; i--)  {
            nums[i] = t % 10;
            t /= 10;
        }
        return n - dfs(0, 0, false, true);
    }
    
    int dfs(int idx, int mask, boolean isNum, boolean isLimit)  {
        if (idx >= cnt)
            return isNum ? 1 : 0;
        
        if (mem[idx][mask][isNum ? 1 : 0][isLimit ? 1 : 0] != null)
            return mem[idx][mask][isNum ? 1 : 0][isLimit ? 1 : 0];
        
        int ans = 0;
        if (!isNum)
            ans += dfs(idx + 1, mask, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? nums[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            if ((mask & (1 << i)) != 0)
                continue;
            ans += dfs(idx + 1, (mask | (1 << i)), true, isLimit && nums[idx] == i);
        }
        
        mem[idx][mask][isNum ? 1 : 0][isLimit ? 1 : 0] = ans;
        return ans;
    }
}