class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put((num % value + value) % value, freq.getOrDefault((num % value + value) % value, 0) + 1);
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < value; i++) {
            if (freq.getOrDefault(i, 0) == 0)
                return i;
            minVal = Math.min(minVal, freq.getOrDefault(i, 0));
        }
        int ans = minVal * value;
        for (int i = 0; i < value; i++)
            if (freq.getOrDefault(i, 0) - minVal == 0)
                return ans + i;
        return -1;
    }
}

// minVal 3 minMod = 3
// 0   1  2 3   4
// 5   6  7 8   9
// 10 11 12 13
