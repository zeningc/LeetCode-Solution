class Solution {
    public String largestNumber(int[] cost, int target) {
        int[] dp = new int[target + 1];
        int[][] map = new int[target + 1][10];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        for (int i = 1; i < 10; i++)
        {
            for (int j = cost[i - 1]; j <= target; j++)
            {
                if (dp[j - cost[i - 1]] == -1 || dp[j] > dp[j - cost[i - 1]] + 1)
                    continue;
                if (dp[j] == dp[j - cost[i - 1]] + 1)
                {
                    map[j] = compareDigitsVector(map[j - cost[i - 1]], i, map[j]);
                    continue;
                }
                dp[j] = dp[j - cost[i - 1]] + 1;
                map[j] = addDigitsVector(map[j - cost[i - 1]], i);
            }
        }
        
        if (dp[target] == -1)
            return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--)
            for (int j = 0; j < map[target][i]; j++)
                sb.append((char)(i + '0'));
        return sb.toString();
    }
    
    int[] compareDigitsVector(int[] a, int d, int[] b)
    {
        int[] aCopy = a.clone();
        aCopy[d]++;
        for (int i = 9; i >= 0; i--)
        {
            if (b[i] == aCopy[i])
                continue;
            if (b[i] > aCopy[i])
                return b;
            return aCopy;
        }
        
        return aCopy;
    }
    
    int[] addDigitsVector(int[] a, int d)
    {
        int[] ret = a.clone();
        ret[d]++;
        return ret;
    }
}

