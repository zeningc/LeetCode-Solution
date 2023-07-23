class Solution {
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        Collections.sort(usageLimits);
        int k = 0;
        long total = 0;
        for (int i = 0; i < usageLimits.size(); i++)    {
            total += usageLimits.get(i);
            if (total >= (long)(k + 1) * (k + 2) / 2)
                k++;
        }
        return k;
    }
}