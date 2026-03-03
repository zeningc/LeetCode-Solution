class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> f = new HashMap<>();
        for (int num : nums)
            f.put(num, f.getOrDefault(num, 0) + 1);
        List<Integer> keys = new ArrayList<>(f.keySet());
        Collections.sort(keys);
        for (int key : keys)  {
            int curFreq = f.get(key);
            if (curFreq == 0)
                continue;
            for (int i = key; i < key + k; i++) {
                if (!f.containsKey(i) || f.get(i) < curFreq)
                    return false;
                f.put(i, f.get(i) - curFreq);
            }
        }

        return true;
    }
}