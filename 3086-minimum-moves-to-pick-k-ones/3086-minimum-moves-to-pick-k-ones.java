class Solution {
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == 1)
                arr.add(i);
        maxChanges = Math.min(maxChanges, k);
        long ans = Long.MAX_VALUE;
        int m = arr.size();
        if (k - maxChanges <= m && maxChanges >= 0)
            ans = Math.min(ans, helper(arr, k - maxChanges) + 2 * maxChanges);
        if (k - maxChanges + 1 <= m && maxChanges >= 1)
            ans = Math.min(ans, helper(arr, k - maxChanges + 1) + 2 * (maxChanges - 1));
        if (k - maxChanges + 2 <= m && maxChanges >= 2)
            ans = Math.min(ans, helper(arr, k - maxChanges + 2) + 2 * (maxChanges - 2));
        if (k - maxChanges + 3 <= m && maxChanges >= 3)
            ans = Math.min(ans, helper(arr, k - maxChanges + 3) + 2 * (maxChanges - 3));
        
        return ans;
    }
    
    long helper(List<Integer> nums, int cnt)   {
        if (cnt == 0)
            return 0;
        long sum = 0;
        for (int i = 0; i < cnt; i++)   {
            sum += Math.abs(nums.get(i) - nums.get(cnt / 2));
        }
        long ans = sum;
        for (int i = 0; i + cnt < nums.size(); i++) {
            long d = nums.get(i + cnt / 2 + 1) - nums.get(i + cnt / 2);
            sum = sum + d * (cnt / 2 + 1) - d * (cnt - (cnt / 2 + 1)) - Math.abs(nums.get(i + cnt / 2 + 1) - nums.get(i)) + Math.abs(nums.get(i + cnt / 2 + 1) - nums.get(i + cnt));
            ans = Math.min(ans, sum);
        }
        
        return ans;
    }
    
}