class Solution {
    public int[] resultsArray(int[][] queries, int k) {
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < queries.length; i++)    {
            
            int curDist = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            if (maxPQ.size() < k)   {
                maxPQ.offer(curDist);
            }
            else if (maxPQ.peek() > curDist)    {
                maxPQ.poll();
                maxPQ.offer(curDist);
            }
            
            if (maxPQ.size() < k)   {
                ans[i] = -1;
            }
            else    {
                ans[i] = maxPQ.peek();
            }
            
        }
        
        return ans;
    }
}