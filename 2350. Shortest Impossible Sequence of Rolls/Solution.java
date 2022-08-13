class Solution {
    public int shortestSequence(int[] rolls, int k) {
        Set<Integer> set = new HashSet<>();
        int n = rolls.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            set.add(rolls[i]);
            if (set.size() == k)    {
                set = new HashSet<>();
                ans++;
            }
        }
        
        return ans + 1;
    }
}
