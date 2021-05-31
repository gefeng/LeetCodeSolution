package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Evaluate the Bracket Pairs of a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/"
)
public class Q1807 {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> dict = new HashMap<>();

        for(List<String> pairs : knowledge) {
            dict.put(pairs.get(0), pairs.get(1));
        }

        int n = s.length();
        int l = -1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                l = i;
            } else if(c == ')') {
                String key = s.substring(l + 1, i);
                String val = dict.getOrDefault(key, "?");
                sb.append(dict.getOrDefault(key, "?"));

                l = -1;
            } else if(l == -1) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
