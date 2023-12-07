class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.size();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        
        int maxFreq = freq.values().stream().max(Integer::compareTo).orElse(0);
        
        if (maxFreq > n / 2)
            return n - (n - maxFreq) * 2;
        return n % 2;
    }
}