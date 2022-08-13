class Solution {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1)
            return "";
        char[] ans = palindrome.toCharArray();
        for (int i = 0; i < (n + 1) / 2; i++)   {
            if (n % 2 == 1 && i == (n + 1) / 2 - 1) {
                ans[n - 1] = 'b';
                return new String(ans);
            }
            if (ans[i] != 'a')  {
                ans[i] = 'a';
                return new String(ans);
            }
        }
        
        ans[n - 1] = 'b';
        return new String(ans);
    }
}
