class Solution {
    int k;
    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        int[] highNums = getNums(high);
        int[] lowNums = low <= 1 ? null : getNums(low - 1);
        int a = dfs(new Integer[highNums.length][11][11][k][2][2], highNums, 0, highNums.length, 0, 0, 0, 0, false, true);
        int b = low <= 1 ? 0 : dfs(new Integer[lowNums.length][11][11][k][2][2], lowNums, 0, lowNums.length, 0, 0, 0, 0, false, true);
        return a - b;
    }
    
    int[] getNums(int num)  {
        
        int t = num;
        int cnt = 0;
        while (t != 0)  {
            t /= 10;
            cnt++;
        }
        
        int[] nums = new int[cnt];
        t = num;
        for (int i = cnt - 1; i >= 0; i--)  {
            nums[i] = t % 10;
            t /= 10;
        }
        
        return nums;
    }
    
    int dfs(Integer[][][][][][] mem, int[] nums, int idx, int cnt, int oddCnt, int evenCnt, int cur, int mod, boolean isNum, boolean isLimit)  {
        if (idx >= cnt) {
            return isNum && oddCnt == evenCnt && mod == 0 ? 1 : 0;
        }
        
        if (mem[idx][oddCnt][evenCnt][mod][isNum ? 1 : 0][isLimit ? 1 : 0] != null)
            return mem[idx][oddCnt][evenCnt][mod][isNum ? 1 : 0][isLimit ? 1 : 0];
        int ans = 0;
        if (!isNum)
            ans += dfs(mem, nums, idx + 1, cnt, 0, 0, 0, 0, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? nums[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            int newOddCnt = oddCnt + (i % 2 == 0 ? 0 : 1);
            int newEvenCnt = evenCnt + (i % 2 == 0 ? 1 : 0);
            ans += dfs(mem, nums, idx + 1, cnt, newOddCnt, newEvenCnt, cur * 10 + i, (cur * 10 + i) % k, true, isLimit && i == nums[idx]);
        }
        
        mem[idx][oddCnt][evenCnt][mod][isNum ? 1 : 0][isLimit ? 1 : 0] = ans;
        return ans;
    }
}