class Solution {
    public String customSortString(String order, String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray())  {
            for (int x = 0; x < freq[c - 'a']; x++)
                sb.append(c);
            freq[c - 'a'] = 0;
        }
        
        for (int i = 0; i < 26; i++)
            if (freq[i] > 0)
                for (int j = 0; j < freq[i]; j++)
                    sb.append((char)(i + 'a'));
        
        return sb.toString();
        
    }
}
