class ATM {
    long[] remain;
    int[] idx2amt;
    public ATM() {
        remain = new long[5];
        idx2amt = new int[] {20,50,100,200,500};
    }
    
    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++)
            remain[i] += banknotesCount[i];
    }
    
    public int[] withdraw(int amount) {
        int[] ret = new int[5];
        for (int i = 4; i > -1; i--)    {
            if (amount >= idx2amt[i] && remain[i] != 0){
                long minus = Math.min((long)amount / idx2amt[i], remain[i]);
                ret[i] += (int)minus;
                amount -= (int)minus * idx2amt[i];
            }
        }
        if (amount == 0)    {
            for (int i = 0; i < 5; i++) {
                remain[i] -= ret[i];
            }
            return ret;
        }
        return new int[] {-1};
    }
}

/**
 * Your ATM object will be instantiated and called as such:
 * ATM obj = new ATM();
 * obj.deposit(banknotesCount);
 * int[] param_2 = obj.withdraw(amount);
 */