class Solution {
    List<Integer>[] listArr;
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        listArr = new List[102];
        for (int i = 0; i <= 101; i++)  {
            listArr[i] = new ArrayList<Integer>(points.length);
        }
        
        for (int[] rec : rectangles)    {
            int l = rec[0];
            int h = rec[1];
            listArr[h].add(l);
        }
        
        for (int i = 0; i <= 101; i++)  {
            Collections.sort(listArr[i]);
        }
        int[] ans = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            int l = points[i][0];
            int h = points[i][1];
            int curr = 0;
            for (int j = h; j <= 101; j++)  {
                curr += binarySearch(j, l);
            }
            ans[i] = curr;
        }
        
        return ans;
    }
    
    int binarySearch(int j, int l)  {
        int left = 0;
        List<Integer> list = listArr[j];
        int right = list.size() - 1;
        
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= l)
                right = mid - 1;
            else
                left = mid + 1;
        }
        
        return list.size() - left;
    }
    
}
