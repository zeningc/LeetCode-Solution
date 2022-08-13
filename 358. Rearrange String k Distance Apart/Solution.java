class Solution {
    public String rearrangeString(String s, int k) {
        if (k == 0)
            return s;
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        Queue<int[]> q = new PriorityQueue<>((a, b) -> b[1] == a[1] ? a[0] - b[0] : b[1] - a[1]);
        for (int i = 0; i < 26; i++)
            if (freq[i] != 0)
                q.offer(new int[] {i, freq[i]});
        
        int idx = 0;
        char[] ans = new char[s.length()];
        while (!q.isEmpty())    {
            int size = q.size();
            List<int[]> list = new ArrayList<>(k);
            for (int i = 0 ; i < k && !q.isEmpty(); i++)
                list.add(q.poll());
            for (int[] curr : list) 
                ans[idx++] = (char)(curr[0] + 'a');
            for (int[] curr : list) {
                if (curr[1] - 1 <= 0)
                    continue;
                q.offer(new int[] {curr[0], curr[1] - 1});
            }
            if (size < k && !q.isEmpty())
                return "";
        }
        
        return new String(ans);
    }
}
