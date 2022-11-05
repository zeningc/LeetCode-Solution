class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        if len(nums)==1:
            return 0
        arr=[nums[-1]*2]
        ans=0
        for i in range(len(nums)-2, -1, -1):
            ans+=bisect.bisect_left(arr, nums[i])
            bisect.insort(arr, nums[i]*2)
        return ans
            
