class Solution {
    public int[] colorTheArray(int n, int[][] queries) {
        int[] colors = new int[n];
        int[] ans = new int[queries.length];
        int cur = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < queries.length; i++)    {
            int idx = queries[i][0];
            int color = queries[i][1];
            if (colors[idx] == color) {
                ans[i] = cur;
                continue;
            }
            Integer lo = map.floorKey(idx);
            boolean connectToLeft = false;
            if (lo != null && colors[lo] != color) {
                int hi = map.get(lo);
                if (hi == idx)  {
                    if (lo == hi)   {
                        map.remove(lo);
                    }
                    else    {
                        map.put(lo, hi - 1);
                        cur--;
                    }
                }
                else if (hi > idx)  {
                    if (lo == idx)  {
                        map.remove(lo);
                        map.put(lo + 1, hi);
                        cur--;
                    }
                    else    {
                        map.put(lo, idx - 1);
                        map.put(idx + 1, hi);
                        cur-=2;
                    }
                }
            }
            
            lo = map.floorKey(idx);
            if (lo != null && map.get(lo) >= idx - 1 && colors[idx - 1] == color)  {
                connectToLeft = true;
                int hi = map.get(lo);
                if (hi == idx - 1)  {
                    map.put(lo, idx);
                    cur++;
                }
            }
            
            boolean connectToRight = false;
            if (idx < n - 1 && colors[idx + 1] == color)    {
                connectToRight = true;
                int hi = map.get(idx + 1);
                map.remove(idx + 1);
                if (connectToLeft)  {
                    map.put(lo, hi);
                }
                else    {
                    map.put(idx, hi);
                }
                cur++;
            }
            
            if (!connectToLeft && !connectToRight)  {
                map.put(idx, idx);
            }
            ans[i] = cur;
            colors[idx] = color;
        }
        
        return ans;
    }
}