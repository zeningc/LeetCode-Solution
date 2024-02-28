class MKAverage {
    int m;
    int k;
    SortedList min;
    SortedList max;
    SortedList mid;
    Queue<Integer> q;
    int n;
    public MKAverage(int m, int k) {
        min = new SortedList();
        mid = new SortedList();
        max = new SortedList();
        this.m = m;
        this.k = k;
        q = new LinkedList<>();
        n = 0;
    }
    
    public void addElement(int num) {
        q.offer(num);
        if (min.cnt == k && max.cnt == k)   {
            if (num > max.peekFirst())  {
                max.offer(num);
                mid.offer(max.pollFirst());
            }
            else if (num < min.peekLast())  {
                min.offer(num);
                mid.offer(min.pollLast());
            }
            else    {
                mid.offer(num);
            }
        }
        else if (min.cnt <= max.cnt) {
            max.offer(num);
            min.offer(max.pollFirst());
        }
        else    {
            min.offer(num);
            max.offer(min.pollLast());
        }
        n++;
        
        if (n <= m)
            return;
        
        int numToRemove = q.poll();
        if (numToRemove <= min.peekLast())   {
            min.decreaseByOne(numToRemove);
            min.offer(mid.pollFirst());
        }
        else if (numToRemove >= max.peekFirst()) {
            max.decreaseByOne(numToRemove);
            max.offer(mid.pollLast());
        }
        else    {
            mid.decreaseByOne(numToRemove);
        }
    }
    
    public int calculateMKAverage() {
        if (n < m)
            return -1;
        return mid.sum / mid.cnt;
    }
    
    
}

class SortedList {
    TreeMap<Integer, Integer> map;
    int sum;
    int cnt;
    
    public SortedList() {
        map = new TreeMap<>();
        sum = 0;
        cnt = 0;
    }
    
    public int pollFirst() {
        return decreaseByOne(peekFirst());
    }
    
    public int pollLast() {
        return decreaseByOne(peekLast());
    }
    
    public int decreaseByOne(int key)  {
        int freq = map.get(key);
        freq--;
        if (freq == 0)
            map.remove(key);
        else
            map.put(key, freq);
        cnt--;
        sum -= key;
        return key;
    }
    
    public void offer(int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
        sum += key;
        cnt++;
        
    }
    
    public int peekFirst()  {
        return map.firstKey();
    }
    
    public int peekLast()  {
        return map.lastKey();
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */