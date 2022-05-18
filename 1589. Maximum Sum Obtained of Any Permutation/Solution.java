class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] arr = new int[n + 1];
        for (int[] req : requests)  {
            arr[req[0]]++;
            arr[req[1] + 1]--;
        }
        
        int[] freq = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += arr[i];
            freq[i] = cnt;
        }
        
        Arrays.sort(freq);
        Arrays.sort(nums);
        long sum = 0;
        long mod = 1000000007;
        for (int i = 0; i < n; i++) {
            sum = (sum + (long)freq[i] * nums[i]) % mod;
        }
        
        return (int)sum;
    }
}
