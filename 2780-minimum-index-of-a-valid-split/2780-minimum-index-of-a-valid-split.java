class Solution {
    public int minimumIndex(List<Integer> nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.size();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        int dominant = -1;
        int maxFreq = -1;
        for (Map.Entry<Integer, Integer> e : freq.entrySet())   {
            int k = e.getKey();
            int v = e.getValue();
            if (v * 2 > n)  {
                dominant = k;
                maxFreq = v;
                break;
            }
        }
        int ans = -1;
        int leftCnt = 0;
        for (int i = 0; i < n - 1; i++) {
            leftCnt = leftCnt + (nums.get(i) == dominant ? 1 : 0);
            int rightCnt = maxFreq - leftCnt;
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