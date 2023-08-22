class Solution {
    public int distMoney(int money, int children) {
        if (money < children)
            return -1;
        if (money < children + 7)
            return 0;
        int maxCnt = Math.min(children, (money - children) / 7);
        int remain = money - maxCnt * 8;
        if (remain == 4 && maxCnt == children - 1 || remain > 0 && maxCnt == children)
            return maxCnt - 1;
        return maxCnt;
    }
}