class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < schedule.size(); i++)   {
            for (int j = 0; j < schedule.get(i).size(); j++)    {
                list.add(new int[] {schedule.get(i).get(j).start, 1});
                list.add(new int[] {schedule.get(i).get(j).end, -1});
            }
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int cnt = 0;
        int start = 0;
        List<int[]> taken = new ArrayList<>();
        for (int[] arr : list)  {
            cnt += arr[1];
            if (cnt == 1 && arr[1] == 1)
                start = arr[0];
            else if (cnt == 0 && arr[1] == -1)  
                taken.add(new int[] {start, arr[0]});
        }
        
        List<Interval> ans = new ArrayList<>();
        for (int i = 0; i < taken.size() - 1; i++)
            ans.add(new Interval(taken.get(i)[1], taken.get(i + 1)[0]));
        
        return ans;
    }
}
