package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "HTML Entity Parser",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/html-entity-parser/"
)
public class Q1410 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String entityParser(String text) {
        int n = text.length();
        Map<String, String> symMap = new HashMap<>() {
            {
                put("&quot;", "\"");
                put("&apos;", "\'");
                put("&amp;", "&");
                put("&gt;", ">");
                put("&lt;", "<");
                put("&frasl;", "/");
            }
        };

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if(c == '&') {
                StringBuilder sym = new StringBuilder();
                int j = i;
                while(j < n && j - i + 1 < 8 && text.charAt(j) != ';') {
                    sym.append(text.charAt(j++));
                }

                if(j < n && text.charAt(j) == ';') {
                    sym.append(';');
                }

                String replace = symMap.get(sym.toString());
                if(replace != null) {
                    sb.append(replace);
                    i = j;
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }


        return sb.toString();
    }
}
