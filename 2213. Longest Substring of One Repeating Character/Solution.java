class Solution {
    TreeMap<Integer, Integer> len;
    TreeMap<Integer, Integer> range;
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int m = queryIndices.length;
        int[] ans = new int[m];
        int i = 0;
        len = new TreeMap<>();
        range = new TreeMap<>();
        while (i < n)   {
            int j = i + 1;
            while (j < n && arr[j] == arr[i])
                j++;
            len.put(j - i, len.getOrDefault(j - i, 0) + 1);
            range.put(i, j);
            i = j;
        }
        
        for (i = 0; i < m; i++) {
            int repIdx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            
            if (c == arr[repIdx])   {
                ans[i] = len.lastKey();
                continue;
            }
            arr[repIdx] = c;
            Integer start = range.floorKey(repIdx);
            Integer end = range.get(start);
            // split
            if (start + 1 != end)   {
                range.remove(start);
                changeFreq(end - start, -1);
                // [start, repIdx] [repIdx, repIdx + 1] [repIdx + 1, end]
                if (repIdx != start)    {
                    range.put(start, repIdx);
                    changeFreq(repIdx - start, 1);
                }
                if (repIdx + 1 != end)  {
                    range.put(repIdx + 1, end);
                    changeFreq(end - repIdx - 1, 1);
                }
                range.put(repIdx, repIdx + 1);
                changeFreq(1, 1);
            }
            
            start = range.floorKey(repIdx);
            end = range.get(start);
            // in this case repIdx must be start
            // if it is not the case, the loop will continue early 
            // when check if c == arr[repIdx]
            if (start == repIdx && repIdx > 0 && arr[repIdx] == arr[repIdx - 1])   {
                Integer prevStart = range.lowerKey(start);
                range.remove(start);
                range.remove(prevStart);
                changeFreq(end - start, -1);
                changeFreq(repIdx - prevStart, -1);
                
                range.put(prevStart, end);
                changeFreq(end - prevStart, 1);
            }
            
            start = range.floorKey(repIdx);
            end = range.get(start);
            if (end - 1 == repIdx && repIdx < n - 1 && arr[repIdx] == arr[repIdx + 1])   {
                Integer nextStart = range.higherKey(start);
                Integer nextEnd = range.get(nextStart);
                range.remove(start);
                range.remove(nextStart);
                changeFreq(nextEnd - nextStart, -1);
                changeFreq(end - start, -1);
                
                range.put(start, nextEnd);
                changeFreq(nextEnd - start, 1);
            }
            
            ans[i] = len.lastKey();
        }
        
        return ans;
    }
    
    void changeFreq(int length, int modify) {
        len.put(length, len.getOrDefault(length, 0) + modify);
        if (len.get(length) == 0)
            len.remove(length);
    }
}