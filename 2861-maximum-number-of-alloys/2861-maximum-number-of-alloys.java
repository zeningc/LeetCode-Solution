class Solution {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int ans = 0;
        for (List<Integer> comp : composition)  {
            int min = Integer.MAX_VALUE;
            List<Integer> copy = new ArrayList<>(stock);
            for (int i = 0; i < comp.size(); i++)
                min = Math.min(min, stock.get(i) / comp.get(i));
            for (int i = 0; i < comp.size(); i++)
                copy.set(i, stock.get(i) - min * comp.get(i));
            int lo = 0;
            int hi = (int)Integer.MAX_VALUE / 2;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (!check(copy, comp, cost, budget, mid))
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            ans = Math.max(ans, min + hi);
        }
        
        return ans;
    }
    
    boolean check(List<Integer> stock, List<Integer> comp, List<Integer> cost, long budget, int mid)   {
        for (int i = 0; i < comp.size(); i++)   {
            if ((long)stock.get(i) >= (long)comp.get(i) * mid)
                continue;
            budget -= (long)cost.get(i) * ((long)comp.get(i) * mid - stock.get(i));
        }
        return budget >= 0;
    }
}