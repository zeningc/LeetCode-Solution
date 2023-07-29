class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int zero = 0;
        int one = 0;
        int mod = (int)1e9 + 7;
        for (char c : binary.toCharArray()) {
            if (c == '0')
                zero = (one + zero) % mod;
            else
                one = (one + zero + 1) % mod;
        }
        
        return (zero + one + (binary.contains("0") ? 1 : 0)) % mod;
    }
}