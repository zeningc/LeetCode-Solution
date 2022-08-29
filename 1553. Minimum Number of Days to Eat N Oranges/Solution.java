class Solution {
    Map<Integer, Integer> mem = new HashMap<>();
    public int minDays(int n) {
        if (n <= 1)
            return n;
        if (mem.containsKey(n))
            return mem.get(n);
        int ans = Math.min(1 + n % 3 + minDays(n / 3), 1 + n % 2 + minDays(n / 2));
        mem.put(n, ans);
        return ans;
    }
}
