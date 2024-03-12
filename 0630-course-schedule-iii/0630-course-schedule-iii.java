class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int used = 0;
        for (int[] course : courses)    {
            int d = course[0];
            int last = course[1];
            used += d;
            pq.offer(course);
            if (used > last)    {
                used -= pq.poll()[0];
            }
        }
        
        return pq.size();
    }
}