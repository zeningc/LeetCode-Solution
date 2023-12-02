class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        TreeSet<Integer> even = new TreeSet<>();
        TreeSet<Integer> odd = new TreeSet<>();
        
        TreeSet<Integer> set;
        
        boolean [] skip = new boolean [n];
        
        for (int num : banned) {
            skip[num] = true;
        }
        
        int start = p;
        
        // Add values to the set that are not banned
        for (int i = 0; i < n; ++i) {
            if (skip[i] || i == p)
                continue;
            
            if (i % 2 == 1)
                odd.add(i);
            else
                even.add(i);
        }
        
        int [] result = new int [n];
        Arrays.fill(result, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(p);
        
        int size;
        int current;
        int moves = 0;
        
        int min, max;
        
        Integer key;
        
        int mCurrent;
        
        while (!queue.isEmpty()) {
            size = queue.size();
            
            
            while (size-- > 0) {
                current = queue.remove();
                
                result[current] = moves;
                
                
                // calculate min index
                if (current < k - 1) {
                    min = (k - 1) - current;
                }else {
                    min = current - k + 1;
                }
                
                // calculate max index
                mCurrent = (n - 1) - current;
                if (mCurrent < k - 1) {
                    max = (k - 1) - mCurrent;
                }else {
                    max = mCurrent - k + 1;
                }
                max = (n - 1) - max;
                
                
                // chose the correct parity set
                set = min % 2 == 0 ? even : odd;
                
                key = set.ceiling(min);
                
                // add all values in range to the queue and remove from set
                while (key != null && key <= max) {
                    queue.add(key);
                    set.remove(key);
                    key = set.ceiling(min);
                }
            }
            
            ++moves;
        }
        
        
        return result;
    }
}