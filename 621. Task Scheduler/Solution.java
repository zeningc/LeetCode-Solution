class Solution {
    public int leastInterval(char[] tasks, int n) {
        n++;
        int[] freq = new int[26];
        
        for (char c : tasks)
            freq[c - 'A']++;
        
        Arrays.sort(freq);
        
        int maxFreq = freq[25];
        int maxFreqCnt = 0;
        
        for (int f : freq)
            if (maxFreq == f)
                maxFreqCnt++;
        
        return Math.max(n * (maxFreq - 1) + maxFreqCnt, tasks.length);
    }
}
