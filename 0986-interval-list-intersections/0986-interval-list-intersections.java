class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m = firstList.length;
        int n = secondList.length;
        
        int p = 0;
        int q = 0;
        
        List<int[]> ansList = new ArrayList<>();
        while (p < m && q < n)  {
            if (firstList[p][1] < secondList[q][0] || firstList[p][0] > secondList[q][1])   {
                if (firstList[p][1] > secondList[q][1])
                    q++;
                else
                    p++;
                continue;
            }
            
            
            int start = Math.max(firstList[p][0], secondList[q][0]);
            int end = Math.min(firstList[p][1], secondList[q][1]);
            ansList.add(new int[] {start, end});
            if (firstList[p][1] > secondList[q][1])
                q++;
            else
                p++;
        }
        
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++)
            ans[i] = ansList.get(i);
        
        return ans;
    }
}