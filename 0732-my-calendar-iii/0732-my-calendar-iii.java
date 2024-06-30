class MyCalendarThree {
    TreeSet<int[]> set;
    int seq;
    public MyCalendarThree() {
        set = new TreeSet<>((a, b) -> a[0] == b[0] ? (b[1] == a[1] ? a[2] - b[2] : b[1] - a[1]) : a[0] - b[0]);
        seq = 0;
    }
    
    public int book(int startTime, int endTime) {
        int cnt = 0;
        int ans = 0;
        TreeSet<int[]> ref = set;
        set.add(new int[] {startTime, 1, seq++});
        set.add(new int[] {endTime - 1, -1, seq++});
        for (int[] cur : set)   {
            cnt += cur[1];
            ans = Math.max(ans, cnt);
        }
        
        
        return ans;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */