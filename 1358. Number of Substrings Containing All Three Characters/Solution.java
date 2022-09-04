class Solution {
    public int numberOfSubstrings(String s) {
        int[] cnt = new int[3];
        int left = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++)    {
            char c = s.charAt(i);
            cnt[c - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0)  {
                if (cnt[s.charAt(left) - 'a'] == 1)
                    break;
                cnt[s.charAt(left) - 'a']--;
                left++;
            }
            if (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0)
                ans += left + 1;
        }
        
        return ans;
    }
}
