class Solution {
    int MOD = (int)1e9 + 7;
    public int countSteppingNumbers(String low, String high) {
        int[] lowArr = new int[low.length()];
        for (int i = 0; i < low.length(); i++)
            lowArr[i] = low.charAt(i) - '0';
        int[] highArr = new int[high.length()];
        for (int i = 0; i < high.length(); i++)
            highArr[i] = high.charAt(i) - '0';
        long a = dfs(new Long[highArr.length][11][2][2], highArr, 0, 0, false, true);
        long b = dfs(new Long[lowArr.length][11][2][2], lowArr, 0, 0, false, true);
        return (int)((MOD + a - b + (check(low) ? 1 : 0)) % MOD);
    }
    
    boolean check(String s) {
        for (int i = 1; i < s.length(); i++)    {
            if (Math.abs(s.charAt(i) - s.charAt(i - 1)) != 1)
                return false;
        }
        return true;
    }
    
    long dfs(Long[][][][] mem, int[] num, int idx, int lastDigit, boolean isNum, boolean isLimit)   {
        if (idx >= num.length)
            return isNum ? 1 : 0;
        if (mem[idx][lastDigit][isNum ? 1 : 0][isLimit ? 1 : 0] != null)
            return mem[idx][lastDigit][isNum ? 1 : 0][isLimit ? 1 : 0];
        
        long ans = 0;
        if (!isNum) {
            ans = (ans + dfs(mem, num, idx + 1, 0, false, false)) % MOD;
            int hi = isLimit ? num[idx] : 9;
            for (int i = 1; i <= hi; i++)    {
                ans = (ans + dfs(mem, num, idx + 1, i, true, isLimit && i == num[idx])) % MOD;
            }
        }
        else    {
            if (lastDigit - 1 >= 0 && (!isLimit || isLimit && lastDigit - 1 <= num[idx]))
                ans = (ans + dfs(mem, num, idx + 1, lastDigit - 1, true, isLimit && lastDigit - 1 == num[idx])) % MOD;
        
            if (lastDigit + 1 <= 9 && (!isLimit || isLimit && lastDigit + 1 <= num[idx]))
                ans = (ans + dfs(mem, num, idx + 1, lastDigit + 1, true, isLimit && lastDigit + 1 == num[idx])) % MOD;
        }
        mem[idx][lastDigit][isNum ? 1 : 0][isLimit ? 1 : 0] = ans;
        return ans;
    }
}