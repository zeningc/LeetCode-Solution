class Solution {
    public long countGood(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int lo = 0;
        long pair = 0;
        for (int hi = 0; hi < nums.length; hi++)    {
            int oldFreq = freq.getOrDefault(nums[hi], 0);
            pair -= (long)oldFreq * (oldFreq - 1) / 2;
            pair += (long)(oldFreq + 1) * oldFreq / 2;
            freq.put(nums[hi], oldFreq + 1);
            
            while (pair >= k)   {
                oldFreq = freq.get(nums[lo]);
                pair -= (long)oldFreq * (oldFreq - 1) / 2;
                pair += (long)(oldFreq - 1) * (oldFreq - 2) / 2;
                if (oldFreq - 1 == 0)
                    freq.remove(nums[lo]);
                else
                    freq.put(nums[lo], oldFreq - 1);
                lo++;
            }
            
            ans += lo;
        }
        
        return ans;
    }
}

/*
1 in a subarray, how to calculate the number of "good" pair?
nums[i] == nums[j] == nums[k] => (i, j) (i, k), (j, k) => 3
n * (n + 1) / 2 => n: number of same element in the array
2. [lo, hi] is not good but [0.. lo - 1, hi] is good
*/