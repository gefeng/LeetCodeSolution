package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Brace Expansion",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/brace-expansion/"
)
public class Q1087 {
    /**
     * Time:  O(M ^ N * log(M ^ N))
     * Space: O(M ^ N)
     * */
    public String[] expand(String s) {
        int n = s.length();
        int pos = 0;
        List<String> cur = new ArrayList<>();
        cur.add("");

        while(pos < n) {
            char c = s.charAt(pos);
            if(c == '{') {
                List<Character> l = new ArrayList<>();
                pos++;
                while(pos < n && s.charAt(pos) != '}') {
                    if(s.charAt(pos) != ',') {
                        l.add(s.charAt(pos));
                    }
                    pos++;
                }

                List<String> next = new ArrayList<>();
                for(String w : cur) {
                    for(char o : l) {
                        next.add(w + o);
                    }
                }

                cur = next;
            } else {
                List<String> next = new ArrayList<>();
                for(String w : cur) {
                    next.add(w + c);
                }
                cur = next;
            }
            pos++;
        }

        Collections.sort(cur);

        return cur.toArray(new String[cur.size()]);
    }
}
