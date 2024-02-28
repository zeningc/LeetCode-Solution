class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        Queue<int[]> sellPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Queue<int[]> buyPQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        
        
        for (int[] order : orders)  {
            int price = order[0];
            int amount = order[1];
            int orderType = order[2];
            if (orderType == 0) {
                while (!sellPQ.isEmpty() && sellPQ.peek()[0] <= price && amount > 0)  {
                    int[] poll = sellPQ.poll();
                    int consume = Math.min(amount, poll[1]);
                    amount -= consume;
                    poll[1] -= consume;
                    if (poll[1] != 0)
                        sellPQ.offer(poll);
                }
                if (amount != 0)    {
                    buyPQ.offer(new int[] {price, amount});
                }
            }
            else    {
                while (!buyPQ.isEmpty() && buyPQ.peek()[0] >= price && amount > 0)  {
                    int[] poll = buyPQ.poll();
                    int consume = Math.min(amount, poll[1]);
                    amount -= consume;
                    poll[1] -= consume;
                    if (poll[1] != 0)
                        buyPQ.offer(poll);
                }
                if (amount != 0)    {
                    sellPQ.offer(new int[] {price, amount});
                }
            }
        }
        
        long ans = 0;
        for (int[] p : buyPQ)
            ans = (ans + p[1]) % 1000000007;
        for (int[] p : sellPQ)
            ans = (ans + p[1]) % 1000000007;
        
        return (int)(ans % 1000000007);
    }
}
