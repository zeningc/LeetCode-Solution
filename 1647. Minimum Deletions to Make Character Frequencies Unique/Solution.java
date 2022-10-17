class Solution {
    public int minDeletions(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        int[] freqMap = new int[s.length() + 1];
        for (int i = 0; i < 26; i++)    {
            if (freq[i] != 0)
                freqMap[freq[i]]++;
        }
        
        Deque<Integer> q = new LinkedList<>();
        int ans = 0;
        for (int i = s.length(); i > 0; i--)   {
            for (int j = 0; j < freqMap[i] - 1; j++)    {
                q.offer(i);
            }
            if (freqMap[i] == 0 && !q.isEmpty())    {
                ans += (q.poll() - i);
            }
        }
        
        while (!q.isEmpty())
            ans += q.poll();
        
        return ans;
        
    }
}
