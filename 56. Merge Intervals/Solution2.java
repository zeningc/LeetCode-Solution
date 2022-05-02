class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>(intervals.length * 2);
        for (int[] interval : intervals)    {
            list.add(new int[] {interval[0], 1});
            list.add(new int[] {interval[1], -1});
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int cnt = 0;
        List<int[]> ans = new ArrayList<>(intervals.length);
        int start = 0;
        int end = 0;
        for (int[] arr : list)  {
            cnt += arr[1];
            if (cnt == 1 && arr[1] == 1)
                start = arr[0];
            else if (cnt == 0 && arr[1] == -1)  {
                end = arr[0];
                ans.add(new int[] {start, end});
            }
        }
        
        return ans.toArray(new int[ans.size()][]);
    }
}
