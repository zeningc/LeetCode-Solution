class Solution {
    public long maximumSum(List<Integer> nums) {
        long ans = 0;
        int n = nums.size();
        int k = 1;
        while (k <= n)  {
            long sum = 0;
            for (int i = 1; k * i * i <= n; i++)    {
                sum += nums.get(k*i*i-1);
            }
            ans = Math.max(ans, sum);
            k++;
        }
        
        return ans;
    }
}