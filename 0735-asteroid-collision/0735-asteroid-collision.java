class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        for (int asteroid : asteroids)  {
            boolean curExploded = false;
            while (!curExploded && !stack.isEmpty() && stack.peek() > 0 && asteroid < 0)    {
                int top = stack.peek();
                if (top >= -asteroid) {
                    curExploded = true;
                }

                if (top <= -asteroid)   {
                    stack.pop();
                }
            }
            if (!curExploded)
                stack.push(asteroid);
        }
        int[] ret = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty())
            ret[i++] = stack.pollLast();
        return ret;
    }
}