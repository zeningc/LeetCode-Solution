class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int[] arr = new int[s.length() + 1];
        for (int[] shift : shifts)  {
            int d = shift[2] == 1 ? 1 : -1;
            arr[shift[0]] += d;
            arr[shift[1] + 1] -= d;
        }
        int cnt = 0;
        int[] bias = new int[s.length()];
        for (int i = 0; i < s.length(); i++)    {
            cnt += arr[i];
            bias[i] = cnt;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)    {
            char c = s.charAt(i);
            int sign = bias[i] < 0 ? -1 : (bias[i] > 0 ? 1 : 0);
            int shift = Math.abs(bias[i]) % 26;
            int idx = c - 'a';
            int nxtIdx = (idx + shift * sign + 26) % 26;
            char dest = (char) ('a' + nxtIdx);
            sb.append(dest);
        }
        
        return sb.toString();
    }
}