class Solution {
    public int maxJump(int[] stones) {
        int ans = 0;
        int i = 0;
        int j = 0;
        int n = stones.length;
        while (i != n - 1)  {
            if (i + 2 <= n - 1) {
                i += 2;
                ans = Math.max(ans, stones[i] - stones[i - 2]);
                if (i == n - 1) {
                    ans = Math.max(ans, stones[n - 1] - stones[n - 2]);
                    i = n - 2;
                    break;
                }
            }
            else    {
                ans = Math.max(ans, stones[n - 1] - stones[i]);
                i = n - 1;
                break;
            }
        }
        
        while (i != 0)  {
            if (i - 2 >= 0) {
                i -= 2;
                ans = Math.max(ans, stones[i + 2] - stones[i]);
            }
            else    {
                ans = Math.max(ans, stones[i] - stones[i - 1]);
                break;
            }
        }
        
        return ans;
        
    }
}