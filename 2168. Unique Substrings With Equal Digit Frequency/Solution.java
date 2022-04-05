class Solution {
    int[] freq;
    public int equalDigitFrequency(String s) {
        freq = new int[10];
        Set<String> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            Arrays.fill(freq, 0);
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - '0']++;
                if (check())
                    set.add(s.substring(i, j + 1));
            }
        }
        return set.size();
    }
    boolean check() {
        int t = 0;
        for (int i = 0; i < 10; i++)    {
            if (freq[i] != 0 && t == 0) {
                t = freq[i];
                continue;
            }
            if(freq[i] != 0 && freq[i] != t)
                return false;
        }
        return true;
    }
}
