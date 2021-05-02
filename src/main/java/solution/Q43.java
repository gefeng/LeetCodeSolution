package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Multiply Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/multiply-strings/"
)
public class Q43 {
    /*
    * 可以先不用管进位，把每位乘积的累加值保存起来，比如 123 * 45
    * bits = [0,0,4,13,22,15]
    * 再来处理进位
    * 这题有几个关键点，
    * 1. m和n位长的数相乘，乘积不超过m+n位
    * 2. 可以用一个长度m+n的数组保存每位乘积累加值
    * 3. 该数组的下标控制i+j+1
    * */
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] bits = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                bits[i + j + 1] += mul;
            }
        }

        for(int i = m + n - 1; i > 0; i--) {
            bits[i - 1] += bits[i] / 10;
            bits[i] = bits[i] % 10;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m + n; i++) {
            if(bits[i] == 0 && sb.length() == 0)
                continue;
            sb.append(bits[i]);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
