package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Fraction Addition and Subtraction",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/fraction-addition-and-subtraction/"
)
public class Q592 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String fractionAddition(String expression) {
        String[] exp = parse(expression);

        int n = exp.length;
        int[] num = new int[n];
        int[] den = new int[n];

        int i = 0;
        for(String s : exp) {
            String[] p = s.split("/");
            int x = Integer.parseInt(p[0]);
            int y = Integer.parseInt(p[1]);

            num[i] = x;
            den[i] = y;
            i++;
        }

        int d = 1;
        for(i = 0; i < n; i++) {
            d = (d * den[i]) / gcd(d, den[i]);
        }

        int sum = 0;
        for(i = 0; i < n; i++) {
            sum += d / den[i] * num[i];
        }

        int g = gcd(Math.abs(sum), d);
        sum /= g;
        d /= g;

        return sum + "/" + d;
    }

    private String[] parse(String expression) {
        int n = expression.length();
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            if(expression.charAt(i) == '/') {
                cnt++;
            }
        }

        String[] s = new String[cnt];
        StringBuilder sb = new StringBuilder();
        for(int i = 0, j = 0; i < n; i++) {
            char c = expression.charAt(i);
            if((c == '+' || c == '-') && sb.length() != 0) {
                s[j++] = sb.toString();
                sb = new StringBuilder();
            }
            sb.append(c);
        }

        s[cnt - 1] = sb.toString();

        return s;
    }

    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
