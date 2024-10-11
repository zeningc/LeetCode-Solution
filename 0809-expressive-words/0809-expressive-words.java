class Solution {
    public int expressiveWords(String s, String[] words) {
        int ans = 0;
        for (String word : words)   {
            int p = 0;
            int q = 0;
            boolean valid = true;
            while (p < s.length() && q < word.length()) {
                char c1 = s.charAt(p);
                char c2 = word.charAt(q);
                if (c1 != c2)   {
                    valid = false;
                    break;
                }
                
                int p1 = p;
                while (p1 < s.length() && s.charAt(p1) == c1)
                    p1++;
                int q1 = q;
                while (q1 < word.length() && word.charAt(q1) == c2)
                    q1++;
                
                int cnt1 = p1 - p;
                int cnt2 = q1 - q;
                if (cnt1 == cnt2 || cnt1 > cnt2 && cnt1 >= 3)   {
                    p = p1;
                    q = q1;
                    continue;
                }
                
                valid = false;
                break;
            }
            
            if (valid && p == s.length() && q == word.length())
                ans++;
        }
        
        return ans;
    }
}