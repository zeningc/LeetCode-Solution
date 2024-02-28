class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> sellPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> buyPQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        
        for (int[] order : orders)  {
            int type = order[2];
            int price = order[0];
            int amount = order[1];
            if (type == 0)  {
                while (!sellPQ.isEmpty() && sellPQ.peek()[0] <= price && amount > 0)  {
                    int[] sellOrder = sellPQ.poll();
                    int sellAmount = sellOrder[1];
                    int consume = Math.min(amount, sellAmount);
                    amount -= consume;
                    sellAmount -= consume;
                    if (sellAmount != 0)    {
                        sellPQ.offer(new int[] {sellOrder[0], sellAmount});
                    }
                }
                
                if (amount != 0)
                    buyPQ.offer(new int[] {price, amount});
            }
            else    {
                while (!buyPQ.isEmpty() && buyPQ.peek()[0] >= price && amount > 0)  {
                    int[] buyOrder = buyPQ.poll();
                    int buyAmount = buyOrder[1];
                    int consume = Math.min(amount, buyAmount);
                    amount -= consume;
                    buyAmount -= consume;
                    if (buyAmount != 0)    {
                        buyPQ.offer(new int[] {buyOrder[0], buyAmount});
                    }
                }
                if (amount != 0)
                    sellPQ.offer(new int[] {price, amount});
            }
        }
        
        long ans = 0;
        int mod = (int)1e9 + 7;
        while (!buyPQ.isEmpty())
            ans = (ans + buyPQ.poll()[1]) % mod;
        
        while (!sellPQ.isEmpty())
            ans = (ans + sellPQ.poll()[1]) % mod;
        
        return (int)(ans % mod);
    }
}