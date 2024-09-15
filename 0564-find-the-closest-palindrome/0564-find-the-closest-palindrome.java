class Solution {
    public String nearestPalindromic(String input) {
        long inputNumber = Long.valueOf(input);
        if (inputNumber < 10)
            return String.valueOf(inputNumber - 1);
        
        int midIndex = input.length() % 2 == 0 ? input.length() / 2 - 1 : input.length() / 2;
        String leftHalfStr = input.substring(0, midIndex + 1);
        long leftHalfNum = Long.valueOf(leftHalfStr);
        
        String smallerPalindrome = generatePalindrome(input, leftHalfStr, leftHalfNum, midIndex, -1);
        String largerPalindrome = generatePalindrome(input, leftHalfStr, leftHalfNum, midIndex, 1);
        String exactPalindrome = generatePalindrome(input, leftHalfStr, leftHalfNum, midIndex, 0);
        
        return findClosestPalindrome(input, smallerPalindrome, findClosestPalindrome(input, largerPalindrome, exactPalindrome));
    }
    
    String generatePalindrome(String input, String leftHalfStr, long leftHalfNum, int midIndex, int adjustment) {
        if (adjustment == -1 && isPowerOfTen(leftHalfStr)) {
            StringBuilder ninePalindrome = new StringBuilder();
            for (int i = 0; i < input.length() - 1; i++)
                ninePalindrome.append('9');
            return ninePalindrome.toString();
        }
        
        if (adjustment == 1 && isPowerOfTenMinusOne(leftHalfStr)) {
            StringBuilder tenPalindrome = new StringBuilder();
            tenPalindrome.append('1');
            for (int i = 0; i < input.length() - 1; i++)
                tenPalindrome.append('0');
            tenPalindrome.append('1');
            return tenPalindrome.toString();
        }
        
        leftHalfNum += adjustment;
        String modifiedLeftHalfStr = String.valueOf(leftHalfNum);
        return createFullPalindrome(modifiedLeftHalfStr, input.length() % 2 == 0 ? midIndex : midIndex - 1);
    }
    
    boolean isPowerOfTen(String leftHalfStr) {
        if (leftHalfStr.charAt(0) != '1')
            return false;
        for (int i = 1; i < leftHalfStr.length(); i++)
            if (leftHalfStr.charAt(i) != '0')
                return false;
        return true;
    }
    
    boolean isPowerOfTenMinusOne(String leftHalfStr) {
        for (char c : leftHalfStr.toCharArray())
            if (c != '9')
                return false;
        return true;
    }
    
    String findClosestPalindrome(String input, String candidate1, String candidate2) {
        long inputNumber = Long.valueOf(input);
        long candidate1Number = Long.valueOf(candidate1);
        long candidate2Number = Long.valueOf(candidate2);
        
        if (inputNumber == candidate1Number)
            return candidate2;
        if (inputNumber == candidate2Number)
            return candidate1;
        
        long diff1 = Math.abs(inputNumber - candidate1Number);
        long diff2 = Math.abs(inputNumber - candidate2Number);
        
        if (diff1 < diff2)
            return candidate1;
        if (diff2 < diff1)
            return candidate2;
        
        return candidate1Number < candidate2Number ? candidate1 : candidate2;
    }
    
    String createFullPalindrome(String leftHalfStr, int midIndex) {
        StringBuilder reversedPart = new StringBuilder(leftHalfStr.substring(0, midIndex + 1));
        reversedPart.reverse();
        return leftHalfStr + reversedPart.toString();
    }
}
