class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        TreeSet<Integer> bs = new TreeSet<>(kmp(s, b));
        List<Integer> as = kmp(s, a);
        List<Integer> ans = new ArrayList<>();
        for (int i : as)    {
            if (bs.floor(i) != null && Math.abs(bs.floor(i) - i) <= k || bs.ceiling(i) != null && Math.abs(bs.ceiling(i) - i) <= k)
                ans.add(i);
        }
        
        return ans;
    }
    
    int[] prefix(String s)  {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j - 1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            pi[i] = j;
        }
        return pi;
    }
    
    List<Integer> kmp(String s, String t)   {
        List<Integer> list = new ArrayList<>();
        int[] pi = prefix(t + "#" + s);
        for (int i = 0; i < pi.length; i++)
            if (pi[i] == t.length())
                list.add(i - 2 * t.length() );
        return list;
    }
}