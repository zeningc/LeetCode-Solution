class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int ans = 0;
        int n = heights.length;
        for (int i = 0; i < n; i++) {
            ans = i;
            if (i == n - 1)
                break;
            int next = heights[i + 1];
            int gap = next - heights[i];
            if (gap <= 0)   {
                continue;
            }
            
            if (bricks >= gap)   {
                bricks -= gap;
                pq.offer(gap);
                continue;
            }
            
            if (ladders == 0)
                break;
            ladders--;
            Integer prevBrick = pq.peek();
            if (prevBrick == null)
                continue;
            if (prevBrick <= gap)   {
                continue;
            }
            pq.poll();
            bricks += prevBrick - gap;
            pq.offer(gap);
        }
        
        return ans;
    }
}