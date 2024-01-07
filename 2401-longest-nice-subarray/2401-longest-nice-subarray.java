class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int[] mem = new int[32];
        Arrays.fill(mem, n);
        int next = 0;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--)    {
            int minIdx = n;
            for (int j = 0; j < 32; j++)
                if ((nums[i] & (1 << j)) != 0)
                    minIdx = Math.min(minIdx, mem[j]);
            int len = Math.min(next + 1, minIdx - i);
            next = len;
            ans = Math.max(ans, len);
            for (int j = 0; j < 32; j++)
                mem[j] = (nums[i] & (1 << j)) == 0 ? mem[j] : i;
            
        }
        
        return ans;
    }
}