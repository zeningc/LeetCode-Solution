class Solution {
    public int minimizeArrayValue(int[] nums) {
        long ans = 0;
        long sum = 0;
        int cnt = 0;
        for (int num : nums)    {
            cnt++;
            sum += num;
            ans = Math.max(ans, (int)Math.ceil((double)sum / cnt));
        }
        
        return (int)ans;
    }
}