package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Kth Bit in Nth Binary String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/"
)
public class Q1545 {
    /**
     * Time:  O(2 ^ N)
     * Space: O(2 ^ N)
     * */
    public char findKthBit(int n, int k) {
        String s = "0";
        while(n != 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("1");
            sb.append(invert(s).reverse());
            s = sb.toString();
            n--;
        }

        return s.charAt(k - 1);
    }

    private StringBuilder invert(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sb.append(c == '0' ? '1' : '0');
        }
        return sb;
    }
}
