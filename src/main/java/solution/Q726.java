package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Problem(
        title = "Number of Atoms",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/number-of-atoms/"
)
public class Q726 {
    char[] s;
    int n;
    int pos;
    public String countOfAtoms(String formula) {
        s = formula.toCharArray();
        n = s.length;
        pos = 0;

        TreeMap<String, Integer> map = new TreeMap(parse());

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> e : map.entrySet()) {
            sb.append(e.getKey());
            if(e.getValue() > 1) {
                sb.append(e.getValue());
            }
        }

        return sb.toString();
    }


    private Map<String, Integer> parse() {
        Map<String, Integer> map = new HashMap<>();

        Map<String, Integer> res = null;
        String name = "";
        while(pos < n) {
            char c = s[pos];

            if(c == '(') {
                pos++;
                res = parse();
                pos++;
                if(pos == n || !Character.isDigit(s[pos])) {
                    for(String k : res.keySet()) {
                        map.put(k, map.getOrDefault(k, 0) + res.get(k));
                    }
                }
            } else if(c == ')') {
                break;
            } else if(Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                pos++;
                while(pos < n && s[pos] >= 'a' && s[pos] <= 'z') {
                    sb.append(s[pos++]);
                }
                name = sb.toString();
                // only one
                if(pos == n || Character.isLetter(s[pos]) || s[pos] == ')' || s[pos] == '(') {
                    map.put(name, map.getOrDefault(name, 0) + 1);
                }
            } else if(Character.isDigit(c)) {
                boolean isElement = Character.isLetter(s[pos - 1]);
                int num = 0;
                while(pos < n && s[pos] >= '0' && s[pos] <= '9') {
                    num = num * 10 + s[pos++] - '0';
                }

                if(isElement) {
                    map.put(name, map.getOrDefault(name, 0) + num);
                    name = "";
                } else {
                    for(String k : res.keySet()) {
                        map.put(k, map.getOrDefault(k, 0) + res.get(k) * num);
                    }
                    res = null;
                }
            }
        }


        return map;
    }
}
