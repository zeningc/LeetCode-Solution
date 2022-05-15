class Solution {
    public int largestCombination(int[] candidates) {
        int[] arr = new int[32];
        
        for (int c : candidates)    {
            for (int i = 0; i < 32; i++)    {
                if ((c & (1 << i)) != 0)
                    arr[i]++;
            }
        }
        
        int ans = 0;
        for (int i = 0; i < 32; i++)
            ans = Math.max(arr[i], ans);
        
        return ans;
    }
}
