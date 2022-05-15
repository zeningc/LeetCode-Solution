class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        int[] arr = new int[n + 2];
        
        for (int[] booking : bookings)  {
            arr[booking[0]] += booking[2];
            arr[booking[1] + 1] -= booking[2];
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++)    {
            cnt += arr[i];
            ans[i - 1] = cnt;
        }
        
        return ans;
    }
}
