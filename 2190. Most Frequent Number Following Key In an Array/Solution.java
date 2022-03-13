class Solution {
    public int mostFrequent(int[] nums, int key) {
        Queue<Integer> pq = new PriorityQueue<>();
        int[] freq = new int[1001];
        for (int i = 0; i < nums.length - 1; i++)   {
            if (nums[i] == key) {
                freq[nums[i + 1]]++; 
            }
        }
        int max = -1;
        int ans = -1;
        for (int i = 0; i < 1001; i++)  {
            if (freq[i] > max)  {
                max = freq[i];
                ans = i;
            }
        }
        
        return ans;
    }
}