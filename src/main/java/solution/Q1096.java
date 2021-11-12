package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Brace Expansion II",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/brace-expansion-ii/"
)
public class Q1096 {
    /**
     * Time:  O(2 ^ N)
     * Space: O(2 ^ N)
     * */
    String exp;
    int pos;
    int len;
    public List<String> braceExpansionII(String expression) {
        exp = expression;
        pos = 0;
        len = exp.length();

        List<String> ans = new ArrayList<>(solve());

        Collections.sort(ans);

        return ans;
    }

    private Set<String> union(Set<String> x, Set<String> y) {
        Set<String> res = new HashSet<>();
        for(String s : x) {
            res.add(s);
        }
        for(String s : y) {
            res.add(s);
        }
        return res;
    }

    private Set<String> conc(Set<String> x, Set<String> y) {
        Set<String> res = new HashSet<>();

        if(x.isEmpty()) {
            x.add("");
        }

        for(String prefix : x) {
            for(String suffix : y) {
                res.add(prefix + suffix);
            }
        }
        return res;
    }

    private Set<String> solve() {
        Set<String> res = new HashSet<>();

        while(pos < len) {
            char c = exp.charAt(pos);

            if(c == '{') {
                pos++;
                res = conc(res, solve());
                pos++;
            } else if(c == ',') {
                pos++;
                res = union(res, solve());
            } else if(c == '}') {
                break;
            } else {
                StringBuilder sb = new StringBuilder();
                while(pos < len && Character.isLetter(exp.charAt(pos))) {
                    sb.append(exp.charAt(pos++));
                }
                res = conc(res, new HashSet<>(Arrays.asList(sb.toString())));
            }
        }
        return res;
    }
}
