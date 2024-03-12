class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
        int used = 0;
        for (int[] course : courses)    {
            int d = course[0];
            int last = course[1];
            if (d + used <= last)   {
                used += d;
                pq.offer(course);
                continue;
            }
            int[] peek = pq.peek();
            if (peek == null || peek[0] < d)   {
                continue;
            }
            pq.poll();
            pq.offer(course);
            used -= peek[0];
            used += d;
        }
        
        return pq.size();
    }
}