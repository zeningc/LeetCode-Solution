class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        return dfs(nums, new HashMap<>(), k, 0);
    }
    
    int dfs(int[] nums, Map<Integer, Integer> freq, int k, int idx) {
        if (idx >= nums.length)
            return !freq.isEmpty() ? 1 : 0;
        int ans = 0;
        ans += dfs(nums, freq, k, idx + 1);
        int curNum = nums[idx];
        if (freq.containsKey(curNum + k) || freq.containsKey(curNum - k))
            return ans;
        freq.put(curNum, freq.getOrDefault(curNum, 0) + 1);
        ans += dfs(nums, freq, k, idx + 1);
        freq.put(curNum, freq.get(curNum) - 1);
        if (freq.get(curNum) == 0)
            freq.remove(curNum);
        return ans;
    }
}
