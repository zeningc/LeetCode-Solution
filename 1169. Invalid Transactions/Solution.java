class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        Record[] records = new Record[n];
        Map<Record, Integer> recordToTranIdxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] split = transactions[i].split(",");
            String name = split[0];
            records[i] = new Record(split[0], split[1], split[2], split[3], i);
            recordToTranIdxMap.put(records[i], i);
        }
        
        Arrays.sort(records, (a, b) -> a.time - b.time);
        
        boolean[] invalid = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            Record record = records[i];
            if (records[i].amount > 1000)
                invalid[recordToTranIdxMap.get(record)] = true;
            for (int j = i - 1; j >= 0 && records[j].time >= records[i].time - 60; j--) {
                if (!records[j].name.equals(records[i].name))
                    continue;
                if (records[j].city.equals(records[i].city))
                    continue;
                invalid[recordToTranIdxMap.get(record)] = true;
                invalid[recordToTranIdxMap.get(records[j])] = true;
            }
        }
        
        List<String> ans = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (invalid[i])
                ans.add(transactions[i]);
                
        return ans;
    }
    
}

class Record    {
    String name;
    int time;
    String city;
    int amount;
    int idx;
    
    public Record(String name, String time, String amount, String city, int idx)   {
        this.name = name;
        this.time = Integer.parseInt(time);
        this.city = city;
        this.amount = Integer.parseInt(amount);
        this.idx = idx;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Record))
            return false;
        Record r = (Record)o;
        return r.name.equals(this.name) && r.city.equals(this.city) && r.amount == this.amount && r.time == this.time;
    }
    
    @Override
    public int hashCode() {
        int code = 0;
        if (this.name != null)
            code += this.name.hashCode();
        if (this.city != null)
            code += this.city.hashCode();
        code += 17 * amount;
        code += time;
        code += 97 * idx;
        return code;
    }
}
