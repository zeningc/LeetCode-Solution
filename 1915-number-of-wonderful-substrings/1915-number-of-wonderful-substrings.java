class Solution {
    public long wonderfulSubstrings(String word) {
        long ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        int state = 0;
        for (char c : word.toCharArray())
        {
            state ^= (1 << (c - 'a'));
            if (cnt.containsKey(state))
                ans += cnt.get(state);
            for (int i = 0; i < 10; i++)
            {
                int targetState = state ^ ((1 << i));
                if (cnt.containsKey(targetState))
                    ans += cnt.get(targetState);
            }
            cnt.put(state, cnt.getOrDefault(state, 0) + 1);
        }
        
        return ans;
    }
}