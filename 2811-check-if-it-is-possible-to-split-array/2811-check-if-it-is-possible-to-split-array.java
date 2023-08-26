class Solution {
    public boolean canSplitArray(List<Integer> nums, int m) {
        for (int i = 0; i < nums.size() - 1; i++)
            if (nums.get(i) + nums.get(i + 1) >= m)
                return true;
        return nums.size() <= 2;
    }
}