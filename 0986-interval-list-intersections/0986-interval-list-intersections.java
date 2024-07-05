class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        int p = 0;
        int q = 0;
        int m = firstList.length;
        int n = secondList.length;
        while (p < m && q < n)  {
            int firstStart = firstList[p][0];
            int firstEnd = firstList[p][1];
            int secondStart = secondList[q][0];
            int secondEnd = secondList[q][1];
            
            if (firstStart > secondEnd) {
                q++;
                continue;
            }
            
            if (secondStart > firstEnd) {
                p++;
                continue;
            }
            
            int intersectStart = Math.max(firstStart, secondStart);
            int intersectEnd = Math.min(firstEnd, secondEnd);
            ans.add(new int[] {intersectStart, intersectEnd});
            
            if (firstEnd > secondEnd)
                q++;
            else
                p++;
        }
        
        return ans.toArray(new int[ans.size()][2]);
    }
}