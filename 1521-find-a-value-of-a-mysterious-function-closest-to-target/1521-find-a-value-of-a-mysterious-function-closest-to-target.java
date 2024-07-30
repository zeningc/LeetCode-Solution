class Solution {
    public int closestToTarget(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        int ans = Integer.MAX_VALUE;
        for (int a : arr)   {
            Set<Integer> nxtSet = new HashSet<>();
            for (int b : set)
                nxtSet.add(b & a);
            nxtSet.add(a);
            set = nxtSet;
            for (int n : nxtSet)
                ans = Math.min(ans, Math.abs(target - n));
        }
        
        return ans;
    }
}