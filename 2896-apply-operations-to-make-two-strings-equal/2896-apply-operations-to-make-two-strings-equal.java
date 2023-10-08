class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length(), done = 0, inf = 1000000, one = inf, last = inf, two = inf;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                last++;
                two++;
            } else if (done < n) {
                one = Math.min(done, two + 1);
                last = Math.min(done, two + x);
                done = two = inf;
            } else {
                done = Math.min(one + x, last + 1);
                two = one;
                one = last = inf;
            }
        }
        return done < inf ? done : -1;
    }
    
}
