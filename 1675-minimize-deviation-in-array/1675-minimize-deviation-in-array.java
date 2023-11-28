class Solution {
    public int minimumDeviation(int[] nums) {
        TreeSet<int[]> treeSet = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = nums.length;
        
        for (int i = 0; i < n; i++)
            treeSet.add(new int[] {nums[i] % 2 == 0 ? nums[i] : nums[i] * 2, i});
        
        int ans = treeSet.last()[0] - treeSet.first()[0];
        while (true)    {
            int[] cur = treeSet.last();
            int num = cur[0];
            treeSet.remove(cur);
            if (num % 2 != 0)
                break;
            treeSet.add(new int[] {num / 2, cur[1]});
            ans = Math.min(ans, treeSet.last()[0] - treeSet.first()[0]);
        }
        
        return ans;
    }
}