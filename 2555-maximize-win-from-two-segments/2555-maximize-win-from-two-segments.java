class Solution {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int lo = 0;
        int sum = 0;
        int maxLeft = 0;
        int maxLeftInd = -1;
        int[] left = new int[n];
        for (int hi = 0; hi < n; hi++)  {
            sum++;
            while (prizePositions[lo] < prizePositions[hi] - k)  {
                sum--;
                lo++;
            }
            left[hi] = hi == 0 ? sum : Math.max(sum, left[hi - 1]);
        }
        lo = n - 1;
        sum = 0;
        int ans = 0;
        for (int hi = n - 1; hi >= 0; hi--) {
            ans = Math.max(left[hi] + sum, ans);
            sum++;
            while (prizePositions[lo] > prizePositions[hi] + k) {
                sum--;
                lo--;
            }
        }
        return ans;
    }
}

