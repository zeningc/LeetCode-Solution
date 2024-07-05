class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int[] r : requests)    {
            treeMap.put(r[0], treeMap.getOrDefault(r[0], 0) + 1);
            treeMap.put(r[1] + 1, treeMap.getOrDefault(r[1] + 1, 0) - 1);
        }
        int[] freq = new int[nums.length];
        int cnt = 0;
        for (int i = 0; i < nums.length; i++)   {
            cnt += treeMap.getOrDefault(i, 0);
            freq[i] = cnt;
        }
        Arrays.sort(freq);
        Arrays.sort(nums);
        long ans = 0;
        for (int i = 0; i < nums.length; i++)   {
            ans = (ans + (long)freq[i] * nums[i]);
        }
        
        return (int)(ans % 1000000007);
    }
}