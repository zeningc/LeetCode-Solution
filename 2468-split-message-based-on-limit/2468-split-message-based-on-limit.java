class Solution {
    public String[] splitMessage(String message, int limit) {
        int splitDigitCnt = -1;
        for (int i = 1; i <= limit - 4; i++)    {
            if (isPossible(message.length(), i, limit)) {
                splitDigitCnt = i;
                break;
            }
        }
        
        if (splitDigitCnt == -1)
            return new String[0];
        StringBuilder placeHolderBuilder = new StringBuilder();
        for (int i = 0; i < splitDigitCnt; i++)
            placeHolderBuilder.append('#');
        String placeHolder = placeHolderBuilder.toString();
        List<String> ans = new ArrayList<>();
        int idx = 0;
        int cnt = 0;
        while (idx < message.length())  {
            String suffix = String.format("<%d/%s>", cnt + 1, placeHolder);
            int nxtIdx = Math.min(idx + (limit - suffix.length()), message.length());
            String messageSegment = message.substring(idx, nxtIdx);
            cnt++;
            idx = nxtIdx;
            ans.add(messageSegment + suffix);
        }
        String cntStr = String.valueOf(cnt);
        String[] ansArray = ans.toArray(new String[0]);
        for (int i = 0; i < cnt; i++)
            ansArray[i] = ansArray[i].replace(placeHolder, cntStr);
        
        return ansArray;
    }
    
    boolean isPossible(int messageLen, int splitCntLen, int limit)  {
        int fixedLen = splitCntLen + 3;
        int cnt = 0;
        int power = 1;
        for (int flexLen = 1; flexLen <= splitCntLen && messageLen > 0; flexLen++)    {
            int consume = (limit - fixedLen - flexLen) * (power * 10 - 1 - cnt);
            cnt += (power * 10 - 1 - cnt);
            messageLen -= Math.min(messageLen, consume);
            power *= 10;
        }
        
        return messageLen == 0;
    }
}