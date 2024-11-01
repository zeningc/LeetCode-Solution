class Solution {
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        long target = ((long)n * (n + 1) / 2 + 1) / 2;
        int lo = 1;
        int hi = n;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, mid) >= target)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    long check(int[] nums, int n) {
        long ans = 0;
        int lo = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int hi = 0; hi < nums.length; hi++)    {
            freq.put(nums[hi], freq.getOrDefault(nums[hi], 0) + 1);
            while (freq.size() > n)  {
                freq.put(nums[lo], freq.get(nums[lo]) - 1);
                if (freq.get(nums[lo]) == 0)
                    freq.remove(nums[lo]);
                lo++;
            }
            ans += (hi - lo + 1);
        }
        return ans;
    }
}



/*
x x x x x o


*/