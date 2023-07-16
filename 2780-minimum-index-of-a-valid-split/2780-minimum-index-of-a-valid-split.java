class Solution {
    public int minimumIndex(List<Integer> nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.size();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        int dominant = -1;
        for (Map.Entry<Integer, Integer> e : freq.entrySet())   {
            int k = e.getKey();
            int v = e.getValue();
            if (v * 2 > n)  {
                dominant = k;
                break;
            }
        }
        
        int[] presum = new int[n + 1];
        int[] sufsum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            presum[i] = presum[i - 1] + (nums.get(i - 1) == dominant ? 1 : 0);
        for (int i = n - 1; i >= 0; i--)
            sufsum[i] = sufsum[i + 1] + (nums.get(i) == dominant ? 1 : 0);
        int ans = -1;
        
        for (int i = 0; i < n - 1; i++) {
            int leftCnt = presum[i + 1];
            int rightCnt = sufsum[i + 1];
            int leftLen = i + 1;
            int rightLen = n - i - 1;
            if (leftCnt * 2 > leftLen && rightCnt * 2 > rightLen)   {
                ans = i;
                break;
            }
        }
        
        
        return ans;
    }
}