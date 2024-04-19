class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = i;
            arr[i][1] = arr1[i];
            arr[i][2] = arr2[i];
        }
        int ans = 0;
        for (int i = 0; i < (1 << 3); i++)  {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int cur = 0;
                for (int k = 0; k < 3; k++) {
                    if ((i & (1 << k)) != 0)
                        cur += arr[j][k];
                    else
                        cur -= arr[j][k];
                }
                max = Math.max(cur, max);
                min = Math.min(cur, min);
            }
            ans = Math.max(ans, max - min);
        }
        
        return ans;
    }
}