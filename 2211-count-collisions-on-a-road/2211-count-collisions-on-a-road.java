class Solution {
    public int countCollisions(String directions) {
        int ret = 0;
        int rightCounter = 0;
        int stopCounter = 0;
        for (char c : directions.toCharArray()) {
            if (c == 'L')   {
                if (rightCounter > 0)   {
                    ret += rightCounter + 1;
                    rightCounter = 0;
                    stopCounter = 1;
                }
                else if (stopCounter > 0)   {
                    ret++;
                }
            }
            else if (c == 'R')  {
                rightCounter++;
                if (stopCounter > 0)
                    stopCounter = 0;
            }
            else if (c == 'S')  {
                ret += rightCounter;
                rightCounter = 0;
                stopCounter = 1;
            }
        }
        
        return ret;
    }
}