package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Reformat The String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reformat-the-string/"
)
public class Q1417 {
    public String reformat(String s) {
        int n = s.length();
        List<Character> c = new ArrayList<>();
        List<Character> d = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(Character.isDigit(s.charAt(i))) {
                d.add(s.charAt(i));
            } else {
                c.add(s.charAt(i));
            }
        }

        if(d.size() != c.size() && Math.abs(d.size() - c.size()) != 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        if(d.size() != c.size()) {
            sb.append(d.size() > c.size() ? d.get(i++) : c.get(j++));
        }

        boolean dFirst = true;
        if(i != 0) {
            dFirst = false;
        }
        while(i < d.size() && j < c.size()) {
            if(dFirst) {
                sb.append(d.get(i++)).append(c.get(j++));
            } else {
                sb.append(c.get(j++)).append(d.get(i++));
            }

        }


        return sb.toString();
    }
}
