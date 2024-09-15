class Solution {
    public String[] splitMessage(String message, int limit) {
        int splitCnt = -1;
        for (int i = 1; i <= message.length(); i++) {
            if (isPossibleToSplit(message.length(), limit, i))  {
                splitCnt = i;
                break;
            }
        }
        if (splitCnt == -1)
            return new String[0];
        String[] ans = new String[splitCnt];
        int idx = 0;
        for (int i = 0; i < splitCnt; i++)  {
            String suffix = String.format("<%d/%d>", i + 1, splitCnt);
            int nxtIdx = Math.min(idx + (limit - suffix.length()), message.length());
            String messageSegment = message.substring(idx, nxtIdx);
            idx = nxtIdx;
            ans[i] = messageSegment + suffix;
        }
        return ans;
    }
    
    
    boolean isPossibleToSplit(int totalLength, int limit, int splitCnt) {
        int fixCnt = getDigitCnt(splitCnt) + 3;
        int flexCnt = 1;
        int nxtPow = 10;
        for (int i = 1; i <= splitCnt && totalLength > 0; i++)  {
            if (i == nxtPow)    {
                flexCnt++;
                nxtPow *= 10;
            }
                
            int consume = limit - fixCnt - flexCnt;
            if (consume <= 0)
                return false;
            totalLength -= Math.min(totalLength, consume);
        }
        
        return totalLength == 0;
    }
    
    int getDigitCnt(int num)    {
        if (num == 0)
            return 1;
        int cnt = 0;
        while (num != 0)    {
            num /= 10;
            cnt++;
        }
        return cnt;
    }
}

