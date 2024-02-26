class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        boolean[][] table = new boolean[128][128];
        for (char[] map : mappings)
            table[map[0]][map[1]] = true;
        for (int i = 0; i < 128; i++)
            table[i][i] = true;
        for (int i = 0; i < s.length() - sub.length() + 1; i++) {
            boolean valid = true;
            for (int j = 0; j < sub.length(); j++)  {
                if (!table[sub.charAt(j)][s.charAt(i + j)])   {
                    valid = false;
                    break;
                }
            }
            if (valid)
                return true;
        }
        return false;
    }
}