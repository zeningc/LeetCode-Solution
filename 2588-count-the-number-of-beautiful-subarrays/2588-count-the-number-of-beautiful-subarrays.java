class Solution {
    public long beautifulSubarrays(int[] A) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);
        int pre = 0;
        long res = 0;
        for (int a : A) {
            pre ^= a;
            int v = dp.getOrDefault(pre, 0);
            res += v;
            dp.put(pre, v + 1);
        }
        return res;
    }
}