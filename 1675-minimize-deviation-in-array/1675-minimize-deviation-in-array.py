class Solution:
    def minimumDeviation(self, nums: List[int]) -> int:
        def smallestRange(matrix):
            l, r=float('-inf'), float('inf')
            max_v=max(row[0] for row in matrix)
            h=[(row[0], i, 0) for i, row in enumerate(matrix)]
            heapq.heapify(h)
            while True:
                min_v, i, p=heapq.heappop(h)
                if max_v-min_v<r-l:
                    l,r=min_v, max_v
                if p==len(matrix[i])-1:
                    return r-l
                heapq.heappush(h, (matrix[i][p+1], i, p+1))
                max_v=max(max_v, matrix[i][p+1])
        matrix=[[] for _ in range(len(nums))]
        for k, v in enumerate(nums):
            layer=[]
            if v&1==1:
                layer.extend([v, v*2])
            else:
                layer.append(v)
                while v&1==0:
                    v=v//2
                    layer.append(v)
                layer=layer[::-1]
            matrix[k]=layer
        return smallestRange(matrix)
                
