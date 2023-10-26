class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        int m = mat.length;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int[] row : mat)
        {
            int minSumLargerThanTarget = Integer.MAX_VALUE;
            Set<Integer> nxt = new HashSet<>();
            for (int r : row)
            {
                for (int i : set)
                {
                    if (i + r <= target)
                    {
                        nxt.add(i + r);
                        continue;
                    }
                    
                    minSumLargerThanTarget = Math.min(minSumLargerThanTarget, i + r);
                }
            }
            if (minSumLargerThanTarget != Integer.MAX_VALUE)
                nxt.add(minSumLargerThanTarget);
            set = nxt;
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i : set)
            ans = Math.min(ans, Math.abs(i - target));
        
        return ans;
    }
}