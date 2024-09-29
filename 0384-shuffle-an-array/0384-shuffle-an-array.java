import java.util.Random;
class Solution {
    int[] nums;
    Random random;
    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    public int[] reset() {
        return nums;
    }
    
    public int[] shuffle() {
        int[] ret = nums.clone();
        for (int i = 0; i < nums.length - 1; i++)   {
            int rand = random.nextInt(i, nums.length);
            int t = ret[i];
            ret[i] = ret[rand];
            ret[rand] = t;
        }
        return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */