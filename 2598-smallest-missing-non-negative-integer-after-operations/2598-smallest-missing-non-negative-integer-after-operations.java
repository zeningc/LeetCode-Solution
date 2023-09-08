class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put((num % value + value) % value, freq.getOrDefault((num % value + value) % value, 0) + 1);
        int minVal = Integer.MAX_VALUE;
        int first = -1;
        for (int i = 0; i < value; i++) {
            int f = freq.getOrDefault(i, 0);
            if (f == 0)
                return i;
            if (minVal > f)   {
                minVal = f;
                first = i;
            }
        }
        return minVal * value + first;
    }
}

// minVal 3 minMod = 3
// 0   1  2 3   4
// 5   6  7 8   9
// 10 11 12 13
