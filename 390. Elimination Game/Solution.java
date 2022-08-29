class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int lastRemaining(int n) {
        if (n == 1)
            return 1;
        if (map.containsKey(n))
            return map.get(n);
        map.put(n,  (n / 2 + 1 - lastRemaining(n / 2)) * 2);
        return map.get(n);
    }
}
