class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int ans=0;
        int n=intervals.length;
        int[] start=new int[n];
        int[] end=new int[n];
        for(int i=0;i<n;i++)    {
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int endPointer=0;
        for(int startPointer=0;startPointer<n;startPointer++)    {
            if(end[endPointer]>start[startPointer]) {
                ans+=1;
            }
            else    {
                endPointer++;
            }
        }
        return ans;
    }
}