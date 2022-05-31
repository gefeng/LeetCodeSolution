package solution.weekly295;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Apply Discount to Prices",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-295/problems/apply-discount-to-prices/"
)
public class Q2288 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String discountPrices(String sentence, int discount) {
        String[] s = sentence.split(" ");
        int n = s.length;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            String w = s[i];
            if(w.charAt(0) == '$') {
                try {
                    double v = Double.parseDouble(w.substring(1, w.length()));
                    v *= (double)(100 - discount) / 100;
                    s[i] = String.format("$%.2f", v);
                } catch(Exception e) {

                }
            }
        }

        for(int i = 0; i < n; i++) {
            sb.append(s[i]);
            if(i < n - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
