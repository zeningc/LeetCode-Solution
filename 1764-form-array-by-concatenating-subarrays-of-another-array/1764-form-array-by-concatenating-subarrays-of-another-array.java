class Solution {
    public boolean canChoose(int[][] groups, int[] nums) {
        int[] pi = new int[3000];
        int start = 0;
        for (int[] group : groups)  {
            boolean valid = false;
            for (int i = 1; i < group.length + 1 + nums.length - start; i++) {
                int j = pi[i - 1];
                while (j > 0 && getNum(nums, group, start, i) != getNum(nums, group, start, j))
                    j = pi[j - 1];
                if (getNum(nums, group, start, i) == getNum(nums, group, start, j))
                    j++;
                pi[i] = j;
                if (i > group.length && pi[i] == group.length)  {
                    valid = true;
                    start = i - group.length;
                    break;
                }
                
            }
            if (!valid)
                return false;
        }
        
        return true;
    }
    
    int getNum(int[] nums, int[] group, int start, int i)   {
        if (i < group.length)
            return group[i];
        if (i == group.length)
            return Integer.MAX_VALUE;
        return nums[start + i - group.length - 1];
    }
}