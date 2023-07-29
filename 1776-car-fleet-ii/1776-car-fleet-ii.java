class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ret = new double[n];
        Arrays.fill(ret, -1.0);
        Deque<double[]> speedRecord = new LinkedList<>();
        speedRecord.offer(new double[] {Double.MAX_VALUE, cars[n - 1][1]});
        for (int i = n - 2; i >= 0; i--)    {
            double curPos = cars[i][0];
            double curSpeed = cars[i][1];
            double nextPos = cars[i + 1][0];
            boolean collide = false;
            double time = 0;
            while (!collide && !speedRecord.isEmpty())  {
                double[] interval = speedRecord.poll();
                double duration = interval[0];
                double nextSpeed = interval[1];
                if ((duration == Double.MAX_VALUE && nextSpeed < curSpeed) || (duration != Double.MAX_VALUE && curPos + curSpeed * duration >= nextPos + nextSpeed * duration))    {
                    // curPos + curSpeed * x = nextPos + nextSpeed * x
                    // (curSpeed - nextSpeed) * x = nextPos - curPos
                    double spentTime = (nextPos - curPos) / (curSpeed - nextSpeed);
                    time += spentTime;
                    ret[i] = time;
                    if (duration == Double.MAX_VALUE)
                        speedRecord.offerFirst(new double[] {Double.MAX_VALUE, nextSpeed});
                    else if (spentTime != duration)
                        speedRecord.offerFirst(new double[] {duration - spentTime, nextSpeed});
                    speedRecord.offerFirst(new double[] {time, curSpeed});
                    collide = true;
                    break;
                }
                if (duration == Double.MAX_VALUE)
                    break;
                time += duration;
                curPos += duration * curSpeed;
                nextPos += duration * nextSpeed;
            }
            
            if (!collide)    {
                speedRecord.offer(new double[] {Double.MAX_VALUE, curSpeed});
            }
        }
        
        return ret;
    }
}