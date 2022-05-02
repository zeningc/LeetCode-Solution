class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> list = new ArrayList<>(2 * schedule.size());
        
        for (int i = 0; i < schedule.size(); i++)   {
            for (int j = 0; j < schedule.get(i).size(); j++)    {
                list.add(new int[] {schedule.get(i).get(j).start, 1});
                list.add(new int[] {schedule.get(i).get(j).end, -1});
            }
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        List<Interval> ans = new LinkedList<>();
        int cnt = 0;
        int start = -1;
        for (int[] arr : list)  {
            if (cnt == 0 && start != -1)    
                ans.add(new Interval(start, arr[0]));

            cnt += arr[1];
            
            if (arr[1] == -1 && cnt == 0)
                start = arr[0];
        }
        
        return ans;
    }
}
