class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        for (int i = total; i >= 0; i -= cost1) {
            ans += (long)i / cost2 + 1;
        }
        return ans;
    }
}
