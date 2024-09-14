class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int idx = 0;
        int preEnd = -1;
        int sum = 0;
        int cnt = 0;
        int n = words.length;
        List<String> ans = new ArrayList<>();
        while (idx < n) {
            if (sum + words[idx].length() + cnt > maxWidth)   {
                if (cnt == 1)   {
                    StringBuilder curLine = new StringBuilder();
                    curLine.append(words[preEnd + 1]);
                    while (curLine.length() < maxWidth)
                        curLine.append(' ');
                    ans.add(curLine.toString());
                    preEnd++;
                    sum = 0;
                    cnt = 0;
                    continue;
                }
                
                int equalEmpty = (maxWidth - sum) / (cnt - 1);
                int moreEmpty = (maxWidth - sum) % (cnt - 1);
                StringBuilder curLine = new StringBuilder();
                for (int i = preEnd + 1; i < idx - 1; i++)  {
                    curLine.append(words[i]);
                    for (int j = 0; j < equalEmpty + (moreEmpty > 0 ? 1 : 0); j++)
                        curLine.append(' ');
                    moreEmpty = Math.max(0, moreEmpty - 1);
                }
                curLine.append(words[idx - 1]);
                ans.add(curLine.toString());
                preEnd = idx - 1;
                sum = 0;
                cnt = 0;
                continue;
            }
            
            sum += words[idx].length();
            cnt++;
            idx++;
        }
        StringBuilder lastLine = new StringBuilder();
        
        for (int i = preEnd + 1; i < n; i++)    {
            lastLine.append(words[i]);
            if (lastLine.length() < maxWidth)
                lastLine.append(' ');
        }
        while (lastLine.length() < maxWidth)
                lastLine.append(' ');
        
        ans.add(lastLine.toString());
        
        return ans;
    }
}