class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] mem = new int[32];
        Arrays.fill(mem, -1);
        int[] ans = new int[n];
        int maxOr = 0;
        for (int i = n - 1; i >= 0; i--)    {
            for (int j = 0; j < 32; j++)
                if ((nums[i] & (1 << j)) != 0)
                    mem[j] = i;

            maxOr |= nums[i];
            int maxIdx = i;
            for (int j = 0; j < 32; j++)
                maxIdx = Math.max(maxIdx, mem[j]);
            ans[i] = maxIdx - i + 1;
        }
        
        return ans;
    }
}