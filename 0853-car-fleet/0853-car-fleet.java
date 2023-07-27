class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] idxes = new Integer[n];
        for (int i = 0; i < n; i++)
            idxes[i] = i;
        Arrays.sort(idxes, (a, b) -> position[a] - position[b]);
        double nextFleetArriveTime = ((double)target - position[idxes[n - 1]]) / speed[idxes[n - 1]];
        int cnt = n;
        for (int i = n - 2; i >= 0; i--)    {
            double curFleetArriveTime = ((double)target - position[idxes[i]]) / speed[idxes[i]];
            if (curFleetArriveTime > nextFleetArriveTime)   {
                nextFleetArriveTime = curFleetArriveTime;
            }
            else    {
                cnt--;
            }
        }
                                                                
        return cnt;                                                 
    }
}

