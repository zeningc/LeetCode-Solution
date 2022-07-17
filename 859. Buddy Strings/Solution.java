class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        if (s.equals(goal)) {
            int[] freq = new int[26];
            for (char c : s.toCharArray())  {
                if (freq[c - 'a'] > 0)
                    return true;
                freq[c - 'a']++;
            }
            return false;
        }
        int n = s.length();
        char sDiff = '#';
        char goalDiff = '#';
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != goal.charAt(i))  {
                cnt++;
                if (cnt > 2)
                    return false;
                if (cnt == 2)   {
                    if (s.charAt(i) != goalDiff || goal.charAt(i) != sDiff)
                        return false;
                }
                if (cnt == 1)   {
                    sDiff = s.charAt(i);
                    goalDiff = goal.charAt(i);
                }
            }
        }
        
        return cnt == 2;
    }
}
