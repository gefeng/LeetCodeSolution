package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Split Concatenated Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/split-concatenated-strings/"
)
public class Q555 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public String splitLoopedString(String[] strs) {
        String res = "";
        int n = strs.length;

        for(int i = 0; i < n; i++) {
            String s = strs[i];
            String rs = reverse(s);
            strs[i] = s.compareTo(rs) > 0 ? s : rs;
        }

        for(int i = 0; i < strs.length; i++) {
            String x = getBest(strs, i, strs[i]);
            String y = getBest(strs, i, reverse(strs[i]));
            String max = x.compareTo(y) > 0 ? x : y;

            res = res.isEmpty() ? max : (res.compareTo(max) > 0 ? res : max);
        }

        return res;
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private String getBest(String[] strs, int cur, String s) {
        String res = "";
        int n = strs.length;
        int len = s.length();
        for(int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder(s.substring(i, len));

            for(int j = 1; j < n; j++) {
                sb.append(strs[(cur + j) % n]);
            }

            sb.append(s.substring(i));

            String cand = sb.toString();

            res = res.isEmpty() ? cand : res.compareTo(cand) > 0 ? res : cand;
        }

        return res;
    }
}
