class Solution {
    public int convertTime(String current, String correct) {
        int currMin = ((current.charAt(0) - '0') * 10 + (current.charAt(1) - '0')) * 60;
        currMin += (current.charAt(3) - '0') * 10 + (current.charAt(4) - '0');
        int corrMin = ((correct.charAt(0) - '0') * 10 + (correct.charAt(1) - '0')) * 60;
        corrMin += (correct.charAt(3) - '0') * 10 + (correct.charAt(4) - '0');
        int gap = corrMin - currMin;
        int ans = 0;
        ans += gap / 60;
        gap %= 60;
        ans += gap / 15;
        gap %= 15;
        ans += gap / 5;
        gap %= 5;
        return ans + gap;
    }
}
