class Solution {
    public int strongPasswordChecker(String password) {
        int digit = 0;
        int lower = 0;
        int upper = 0;
        int n = password.length();
        for (char c : password.toCharArray())   {
            if (c >= '0' && c <= '9')
                digit = 1;
            if (c >= 'a' && c <= 'z')
                lower = 1;
            if (c >= 'A' && c <= 'Z')
                upper = 1;
        }
        int m = digit + lower + upper;
        if (n < 6)
            return Math.max(6 - n, 3 - m);
        int[] cnts = new int[3];
        int ans = 0;
        int idx = 0;
        while (idx < n)   {
            int j = idx + 1;
            while (j < n && password.charAt(j - 1) == password.charAt(j))
                j++;
            int cnt = j - idx;
            if (cnt >= 3)   {
                ans += cnt / 3;
                cnts[cnt % 3]++;
            }
            
            idx = j;
        }

        if (n >= 6 && n <= 20)
            return Math.max(3 - m, ans);
        
        int base = n - 20;
        int curr = base;
        
        for (int i = 0; i < 3; i++) {
            if (i == 2)
                cnts[i] = ans;
            if (cnts[i] != 0 && curr != 0)  {
                int t = Math.min(cnts[i] * (i + 1), curr);
                curr -= t;
                ans -= t / (i + 1);
            }
        }
        
        return base + Math.max(3 - m, ans);
    }
}
