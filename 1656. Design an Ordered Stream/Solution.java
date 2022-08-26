class OrderedStream {
    String[] arr;
    int ptr;
    int n;
    public OrderedStream(int n) {
        arr = new String[n];
        ptr = 0;
        this.n = n;
    }
    
    public List<String> insert(int idKey, String value) {
        List<String> ans = new LinkedList<>();
        idKey--;
        arr[idKey] = value;
        while (ptr < n && arr[ptr] != null) {
            ans.add(arr[ptr]);
            ptr++;
        }
        
        return ans;
    }
}
