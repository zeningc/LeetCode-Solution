class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = n - 1;
        int ans = 0;
        int left = nums[i];
        int right = nums[j];
        while (i < j)   {
            if (left == right)  {
                i++;
                j--;
                left = i < n ? nums[i] : 0;
                right = j >= 0 ? nums[j] : 0;
                continue;
            }
            
            if (right < left)    {
                int cur = j - 1;
                while (right < left && i <= cur)    {
                    right += nums[cur];
                    ans++;
                    cur--;
                }
                j = cur + 1;
            }
            else if (right > left)    {
                int cur = i + 1;
                while (right > left && cur <= j)    {
                    left += nums[cur];
                    ans++;
                    cur++;
                }
                i = cur - 1;
            }
        }
        
        return ans;
    }
}