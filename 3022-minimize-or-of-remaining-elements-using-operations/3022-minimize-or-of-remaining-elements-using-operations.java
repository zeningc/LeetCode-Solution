class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n];
        int ans = 0;
        for (int i = 31; i >= 0; i--)   {
            for (int j = 0; j < n; j++)
                arr[j] = (arr[j] << 1) + ((nums[j] >> i) & 1);
            
            if (!check(arr, k))    {
                for (int l = 0; l < n; l++)
                    arr[l] = (arr[l] >> 1);
                ans |= (1 << i);
            }
        }
        
        return ans;
    }
    
    
    boolean check(int[] arr, int k) {
        int n = arr.length;
        int cur = 0;
        int cnt = 0;
        int i = 0;
        while (i < n)   {
            int j = i;
            cur = arr[i];
            while (cur != 0)   {
                cnt++;
                j++;
                if (j == n)
                    break;
                cur &= arr[j];
            }
            i = j + 1;
        }
        
        return cnt <= k && cnt != n;
    }
}