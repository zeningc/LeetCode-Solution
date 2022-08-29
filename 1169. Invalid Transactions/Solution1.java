class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        Record[] records = new Record[n];
        for (int i = 0; i < n; i++) {
            String[] split = transactions[i].split(",");
            String name = split[0];
            records[i] = new Record(split[0], split[1], split[2], split[3]);
        }
        
        boolean[] invalid = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            Record record = records[i];
            if (records[i].amount > 1000)
                invalid[i] = true;
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (records[j].time > record.time || records[j].time < record.time - 60)
                    continue;
                if (!records[j].name.equals(records[i].name))
                    continue;
                if (records[j].city.equals(records[i].city))
                    continue;
                invalid[i] = true;
                invalid[j] = true;
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
    
    public Record(String name, String time, String amount, String city)   {
        this.name = name;
        this.time = Integer.parseInt(time);
        this.city = city;
        this.amount = Integer.parseInt(amount);
    }
    
}