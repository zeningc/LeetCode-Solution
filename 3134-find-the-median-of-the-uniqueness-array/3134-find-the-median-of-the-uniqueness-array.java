class Solution {
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        long total = (long)n * (n + 1) / 2;
        int lo = 1;
        int hi = n;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, mid, total))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    boolean check(int[] nums, int mid, long total)   {
        long target = (total + 1) / 2;
        int lo = 0;
        long cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int hi = 0; hi < nums.length; hi++)    {
            map.put(nums[hi], map.getOrDefault(nums[hi], 0) + 1);
            while (map.size() > mid) {
                map.put(nums[lo], map.get(nums[lo]) - 1);
                if (map.get(nums[lo]) == 0)
                    map.remove(nums[lo]);
                lo++;
            }
            
            cnt += (hi - lo + 1);
        }
        
        return cnt >= target;
    }
}
// 4 3 5 4
// 43 35 54
// 435 354
// 4354
    
// 1 1 1 1 2 2 2 3 3 3
