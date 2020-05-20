package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Greatest Common Divisor of Strings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/greatest-common-divisor-of-strings/"
)
public class Q1071 {
    /*Euclidean algorithm 这个要记住*/
    private int calGCD(int a, int b) {
        if(b == 0) return a;
        return calGCD(b, a % b);
    }
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int gcd = calGCD(len1, len2);
        String divisor = str1.substring(0, gcd);
        String concatStr = str1 + str2;
        for(int i = 0; i < len1 + len2; i+= gcd) {
            if(!divisor.equals(concatStr.substring(i, i + gcd)))
                return "";
        }
        return divisor;
    }

    public String gcdOfStringsRecursive(String str1, String str2) {
        if(str2.length() < str1.length())
            return gcdOfStringsRecursive(str2, str1);
        if(!str2.startsWith(str1))
            return "";
        if(str1.isEmpty())
            return str2;
        return gcdOfStringsRecursive(str1, str2.substring(str1.length()));
    }
}
