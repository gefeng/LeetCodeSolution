package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Complex Number Multiplication",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/complex-number-multiplication/"
)
public class Q537 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public String complexNumberMultiply(String num1, String num2) {
        int[] x = parse(num1);
        int[] y = parse(num2);

        int real = x[0] * y[0] - x[1] * y[1];
        int imag = x[0] * y[1] + x[1] * y[0];

        return toString(real, imag);
    }

    private int[] parse(String num) {
        String[] s = num.split("\\+");
        String real = s[0];
        String imag = s[1];
        int x = Integer.parseInt(real);
        int y = Integer.parseInt(imag.substring(0, imag.length() - 1));

        return new int[] {x, y};
    }

    private String toString(int real, int imag) {
        String x = Integer.toString(real);
        String y = Integer.toString(imag);

        return x + "+" + y + "i";
    }
}
