package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Additive Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/additive-number/"
)
public class Q306 {
    /**
     * Can be further optimized (prune branch), but i am lazy.
     *
     * Time:  O(2 ^ N)
     * Space: O(N)
     * */
    public boolean isAdditiveNumber(String num) {
        return dfs(num, 0, new ArrayList<>());
    }

    private boolean dfs(String s, int start, List<String> seq) {
        int n = s.length();
        if(start == n) {
            return isAdditive(seq);
        }

        if(s.charAt(start) == '0') {
            seq.add("0");
            if(dfs(s, start + 1, seq)) {
                return true;
            }
            seq.remove(seq.size() - 1);
        } else {
            for(int i = start; i < n; i++) {
                String ss = s.substring(start, i + 1);
                seq.add(ss);

                if(dfs(s, i + 1, seq)) {
                    return true;
                }

                seq.remove(seq.size() - 1);
            }
        }

        return false;
    }

    private boolean isAdditive(List<String> seq) {
        int n = seq.size();
        if(n < 3) {
            return false;
        }

        String x = seq.get(0);
        String y = seq.get(1);
        for(int i = 2; i < n; i++) {
            String s = seq.get(i);
            if((x.length() > 1 && x.charAt(0) == '0') ||
                    (y.length() > 1 && y.charAt(0) == '0')) {
                return false;
            }

            if(!s.equals(add(x, y))) {
                return false;
            }

            x = y;
            y = s;
        }

        return true;
    }

    private String add(String x, String y) {
        StringBuilder sb = new StringBuilder();
        int m = x.length();
        int n = y.length();
        int i = m - 1;
        int j = n - 1;
        int carry = 0;
        while(i >= 0 || j >= 0) {
            int a = i < 0 ? 0 : x.charAt(i--) - '0';
            int b = j < 0 ? 0 : y.charAt(j--) - '0';
            sb.append((a + b + carry) % 10);
            carry = (a + b + carry) / 10;
        }
        if(carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
