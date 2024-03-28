class Solution:
    def minimumCoins(self, prices: List[int]) -> int:
        n = len(prices)
        buy, not_buy = prices[0], inf
        heap = [(prices[0], 1)]
        for i in range(2, n + 1):
            buy = min(buy, not_buy) + prices[i - 1]
            t = ceil(i / 2)
            while heap[0][1] < t:
                heappop(heap)
            not_buy = heap[0][0]
            heappush(heap, (buy, i))
        return min(buy, not_buy)

