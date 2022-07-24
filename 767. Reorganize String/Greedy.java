class Solution {
    public String reorganizeString(String s) {
        int[] freq = new int[26];
        
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        
        List<Pair> list = new LinkedList<>();
        
        for (char c = 'a'; c <= 'z'; c++)
            if (freq[c - 'a'] != 0)
                list.add(new Pair(c, freq[c - 'a']));
        
        Collections.sort(list, (a, b) -> b.freq - a.freq);
        char[] ans = new char[s.length()];
        int pos = 0;
        for (Pair p : list) {
            for (int i = 0; i < p.freq; i++)    {
                ans[pos] = p.c;
                if (pos >= 1 && ans[pos - 1] == ans[pos])
                    return "";
                pos += 2;
                if (pos >= ans.length)
                    pos = 1;
            }
        }
        
        return new String(ans);
    }
    
}

class Pair  {
    char c;
    int freq;
    
    public Pair(char c, int freq)   {
        this.c = c;
        this.freq = freq;
    }
}
