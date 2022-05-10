class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] freq = new int[101][n + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 100; j++)   {
                freq[j][i + 1] = freq[j][i];
            }
            freq[nums[i]][i + 1] = freq[nums[i]][i] + 1;
        }
        
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int l = query[0];
            int r = query[1];
            int prev = -1;
            int currAns = 101;
            for (int j = 1; j <= 100; j++)  {
                if (freq[j][r + 1] - freq[j][l] > 0)    {
                    if (prev == -1)  {
                        prev = j;
                        continue;
                    }
                    currAns = Math.min(currAns, j - prev);
                    prev = j;
                }
            }
            if (currAns == 101)
                ans[i] = -1;
            else
                ans[i] = currAns;
        }

        return ans;
    }    
}

