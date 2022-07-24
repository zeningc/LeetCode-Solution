class Solution {
    public long numberOfWeeks(int[] milestones) {
        long sum = 0;
        long max = 0;
        for (int m : milestones)  {
            sum += m;
            max = Math.max(max, m);
        }
        
        if (max <= sum / 2)
            return sum;
        
        return (sum - max) * 2 + 1;
    }
}
