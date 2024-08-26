class Solution:
    def countKConstraintSubstrings(self, s: str, k: int, queries: List[List[int]]) -> List[int]:
        counts = Counter()
        left = 0
        n = len(s)
        left_to_right = [0] * n
        right_to_left = [0] * n

        # For each right, find the minimum left
        for right in range(n):
            counts[s[right]] += 1
            while counts['0'] > k and counts['1'] > k:
                counts[s[left]] -= 1
                left += 1
            right_to_left[right] = left

        # For each left, find the maximum right
        right = n - 1
        counts = Counter()
        for left in reversed(range(n)):
            counts[s[left]] += 1
            while counts['0'] > k and counts['1'] > k:
                counts[s[right]] -= 1
                right -= 1
            left_to_right[left] = right

        # For the right indexes that has left within the query bounds, all the substrings count
        # For the right indexes that has left outside the query bounds, only substrings within the bounds will count
        # So, seperate the cases for each query
        
        pref_sum = [0] + list(accumulate([(right - left + 1) for right, left in enumerate(right_to_left)]))
        result = []
        for left_bound, right_bound in queries:
            middle = min(right_bound, left_to_right[left_bound])
            length = middle - left_bound + 1
            curr = length * (length + 1) // 2
            curr += pref_sum[right_bound + 1] - pref_sum[middle + 1] # where left is within, so all the substrings count
            result.append(curr)
        return result