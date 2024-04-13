class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        List<int[]> numList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            numList.add(new int[] {i, nums[i]});
        Collections.sort(numList, (a, b) -> a[1] - b[1]);
        long sum = 0;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++)   {
            int[] cur = numList.get(i);
            int idx = cur[0];
            int num = cur[1];
            ans[idx] += (int)((long)i * num - sum);
            sum += num;
        }
        sum = 0;
        for (int i = nums.length - 1; i >= 0; i--)   {
            int[] cur = numList.get(i);
            int idx = cur[0];
            int num = cur[1];
            ans[idx] += (int)(sum - (long)(nums.length - i - 1) * num);
            sum += num;
        }
        
        return ans;
    }
}