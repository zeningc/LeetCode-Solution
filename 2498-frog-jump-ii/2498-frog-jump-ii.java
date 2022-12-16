class Solution {
    public int maxJump(int[] stones) {
        int ans = 0;
        int n = stones.length;
        if (n <= 3) {
            return stones[n - 1];
        }
        int i = 0;
        int last = 0;
        while (i != n - 1)   {
            last = i;
            if (i >= 2)  {
                ans = Math.max(ans, stones[i] - stones[i - 2]);
            }
            i = Math.min(i + 2, n - 1);
        }
        ans = Math.max(ans, stones[i] - stones[last]);
        if (last == n - 3){
            i = n - 2;
            ans = Math.max(ans, stones[n - 1] - stones[n - 2]);
        }

        while (i != 0)  {
            last = i;
            if (i <= n - 3)  {
                ans = Math.max(ans, stones[i + 2] - stones[i]);
            }
            i = Math.max(0, i - 2);
        }
        ans = Math.max(ans, stones[last] - stones[0]);
        return ans;
    }
}