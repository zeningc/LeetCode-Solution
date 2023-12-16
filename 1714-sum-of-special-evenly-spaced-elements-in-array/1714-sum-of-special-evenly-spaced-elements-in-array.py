class Solution:
    def solve(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        
        n = len(nums)
        dividingPoint = int(sqrt(n))
        mod = 10 ** 9 + 7
        
        prefix = {}
        for y in range(1, dividingPoint+1):
            for startingPoint in range(y):
                prefix[(startingPoint, y)] = [0]
                sm = 0
                x = startingPoint
                while x < n:
                    sm += nums[x]
                    sm %= mod
                    prefix[(startingPoint, y)].append(sm)
                    x += y
                    
                    
        res = []
        for x, y in queries:
            if y > dividingPoint:
                sm = 0
                while x < n:
                    sm += nums[x]
                    sm %= mod
                    x += y
                res.append(sm)
            else:
                startingPoint = x % y
                res.append((prefix[(startingPoint, y)][-1] - prefix[(startingPoint, y)][x//y]) % mod)
        return res