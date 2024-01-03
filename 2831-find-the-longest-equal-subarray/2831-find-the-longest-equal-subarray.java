/*
The philosophy here is that:
1. we maintain m such that m is a legal representation of the frequency
inside the window.
2. the length of longest array can only be incresed one at a time
3. we want to maintain the window such that it is legal

So at the end of each iteration, we use the following statement to make the
window legal:
if (hi - lo + 1 - maxFreq > k)    {
    m.put(nums.get(lo), m.get(nums.get(lo)) - 1);
    lo++;
}
It is not intuitive that we are trying to maintain a window by
hi - lo + 1 - maxFreq > k
we have already made maxFreq array
if there is a better solution, and the maxFreq gets updated
the length of the window will only be the current window + the new element we found
*/
class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        int lo = 0;
        int maxFreq = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for (int hi = 0; hi < n; hi++) {
            int num = nums.get(hi);
            m.put(num, m.getOrDefault(num, 0) + 1);
            maxFreq = Math.max(maxFreq, m.get(num));
            if (hi - lo + 1 - maxFreq > k)    {
                m.put(nums.get(lo), m.get(nums.get(lo)) - 1);
                lo++;
            }
        }
        return maxFreq;
    }
}