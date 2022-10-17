class Solution {
    public int minDeletions(String s) {
        int[] frequency = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
        }
        
        Arrays.sort(frequency);
        
        int deleteCount = 0;
        int maxFreqAllowed = s.length();
        
        for (int i = 25; i >= 0 && frequency[i] > 0; i--) {
            if (frequency[i] > maxFreqAllowed) {
                deleteCount += frequency[i] - maxFreqAllowed;
                frequency[i] = maxFreqAllowed;
            }
            maxFreqAllowed = Math.max(0, frequency[i] - 1);
        }
        
        return deleteCount;
    }     
}
