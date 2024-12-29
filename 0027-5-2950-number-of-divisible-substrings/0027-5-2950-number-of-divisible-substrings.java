class Solution {
    public int countDivisibleSubstrings(String word) {
        int ans = 0;
        for (int i = 0; i < word.length(); i++) {
            int sum = 0;
            for (int j = i; j < word.length(); j++) {
                sum += charToInt(word.charAt(j));
                if (sum % (j - i + 1) == 0)
                    ans++;
            }
        }
        
        return ans;
    }
    
    int charToInt(char c)   {
        if (c - 'a' <= 1)
            return 1;
        return (c - 'a' - 2) / 3 + 2;
    }
}