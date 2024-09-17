class Solution {
    Map<Integer, String> oneToNine = Map.of(
        1, "One",
        2, "Two",
        3, "Three",
        4, "Four",
        5, "Five",
        6, "Six",
        7, "Seven",
        8, "Eight",
        9, "Nine"
    );
    Map<Integer, String> tenToNineteen = Map.of(
        10, "Ten",
        11, "Eleven",
        12, "Twelve",
        13, "Thirteen",
        14, "Fourteen",
        15, "Fifteen",
        16, "Sixteen",
        17, "Seventeen",
        18, "Eighteen",
        19, "Nineteen"
    );
    Map<Integer, String> TwentyToNinety = Map.of(
        20, "Twenty",
        30, "Thirty",
        40, "Forty",
        50, "Fifty",
        60, "Sixty",
        70, "Seventy",
        80, "Eighty",
        90, "Ninety"
    );
    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        String[] units = new String[] {"", "Thousand", "Million", "Billion"};
        int cnt = countDigit(num);
        int[] powers = new int[cnt + 1];
        powers[0] = 1;
        for (int i = 1; i <= cnt; i++)
            powers[i] = powers[i - 1] * 10;
        String ret = "";
        while (cnt > 0) {
            if (num == 0)
                break;
            int unitLevel = (cnt - 1) / 3;
            if (num / powers[unitLevel * 3] == 0)   {
                cnt = unitLevel * 3;
                continue;
            }
            String curLevelStr = convertNumberLessThanOneThousand(num / powers[unitLevel * 3]);
            if (ret.length() > 0)
                ret += " ";
            if (unitLevel != 0) 
                ret += curLevelStr + " " + units[unitLevel];
            else
                ret += curLevelStr;
            num -= powers[unitLevel * 3] * (num / powers[unitLevel * 3]);
            cnt = unitLevel * 3;
        }
        return ret;
    }
    
    int countDigit(int num) {
        int cnt = 0;
        while (num != 0)    {
            cnt++;
            num /= 10;
        }
        
        return cnt;
    }
    
    String convertNumberLessThanOneThousand(int num) {
        String hundredStr = "";
        if (num / 100 != 0) {
            hundredStr = oneToNine.get(num / 100) + " Hundred";
        }
        String tenStr = "";
        String oneStr = "";
        num %= 100;
        if (num < 10)   {
            if (num != 0)   {
                oneStr = oneToNine.get(num);
            }
        }
        else if (num < 20)  {
            tenStr = tenToNineteen.get(num);
        }
        else    {
            if (num % 10 != 0)
                oneStr = oneToNine.get(num % 10);
            tenStr = TwentyToNinety.get((num / 10) * 10);
        }
        
        String ret = "";
        if (hundredStr.length() > 0)
            ret += hundredStr;
        if (tenStr.length() > 0)  {
            if (ret.length() > 0)
                ret += " ";
            ret += tenStr;
        }
        if (oneStr.length() > 0)  {
            if (ret.length() > 0)
                ret += " ";
            ret += oneStr;
        }
        
        return ret;
    }
}