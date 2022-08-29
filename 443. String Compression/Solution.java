class Solution {
    public int compress(char[] chars) {
        int ans = 0;
        int i = 0;
        int n = chars.length;
        int ptr = 0;
        while (i < n)   {
            int j = i + 1;
            while (j < n && chars[j] == chars[j - 1])
                j++;
            chars[ptr++] = chars[i];
            if (j == i + 1) {
                ans++;
            }
            else    {
                int cnt = j - i;
                List<Integer> list = new ArrayList<>();
                while (cnt != 0)    {
                    list.add(cnt % 10);
                    cnt /= 10;
                }
                Collections.reverse(list);
                ans += list.size() + 1;
                for (int digit : list)
                    chars[ptr++] = (char)(digit + '0');
            }
            i = j;
        }
        
        return ans;
    }
}
