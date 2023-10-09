class Solution {
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        Map<Character, Integer> vowelToIndex = Map.of('a', 0, 'e', 1, 'i', 2, 'o', 3, 'u', 4);
        int[] firstSeen = new int[32];
        Arrays.fill(firstSeen, -1);
        int state = 0, maxLen = 0;
        firstSeen[0] = -1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (vowelToIndex.containsKey(c)) {
                state ^= (1 << vowelToIndex.get(c));
            }
            if (firstSeen[state] == -1 && state != 0) {
                firstSeen[state] = i;
            } else {
                maxLen = Math.max(maxLen, i - firstSeen[state]);
            }
        }
        return maxLen;
    }
}
