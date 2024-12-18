class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        def most(k):
            if k<0:
                return 0
            l=r=0
            count=0
            res=0
            for r in range(len(nums)):
                count+=nums[r]
                while count>k:
                    count-=nums[l]
                    l+=1
                res+=r-l+1
            return res
        return most(goal)-most(goal-1)
