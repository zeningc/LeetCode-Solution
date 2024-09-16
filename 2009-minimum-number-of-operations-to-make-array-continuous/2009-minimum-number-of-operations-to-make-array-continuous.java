class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        List<Integer> distinctNumList = new ArrayList<>(set);
        Collections.sort(distinctNumList);
        int hi = 0;
        int ans = Integer.MAX_VALUE;
        for (int lo = 0; lo < distinctNumList.size(); lo++) {
            while (hi < distinctNumList.size() && distinctNumList.get(hi) - distinctNumList.get(lo) + 1 <= n) {
                ans = Math.min(ans, n - (hi - lo + 1));
                hi++;
            }
        }
        
        return ans;
    }
}