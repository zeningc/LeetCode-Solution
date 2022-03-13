class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        TreeSet<Integer> ts = new TreeSet<>();
        List<Integer> ans = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == key)
                ts.add(i);
        }

        for (int i = 0; i < n; i++) {
            Integer left = ts.floor(i);
            if (left != null && i - left <= k) {
                ans.add(i);
                continue;
            }
            Integer right = ts.ceiling(i);
            if (right != null && right - i <= k) {
                ans.add(i);
                continue;
            }
        }

        return ans;
    }
}