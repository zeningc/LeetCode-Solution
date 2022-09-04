class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int[] first = new int[26];
        int[] second = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(second, -1);
        
        for (int i = 0; i < s.length(); i++)  {
            char c = s.charAt(i);
            if (first[c - 'a'] == -1)
                first[c - 'a'] = i;
            else
                second[c - 'a'] = i;
        }
        
        for (int i = 0; i < 26; i++)    {
            if (first[i] != -1) {
                if (second[i] - first[i] - 1 != distance[i])
                    return false;
            }
        }
        
        return true;
    }
}
