package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Unique Email Addresses",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/unique-email-addresses/"
)
public class Q929 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public int numUniqueEmails(String[] emails) {
        Set<String> valid = new HashSet<>();
        for(String e : emails) {
            String[] s = e.split("@");
            String ln = s[0];
            String dn = s[1];

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < ln.length(); i++) {
                char c = ln.charAt(i);
                if(c == '.') {
                    continue;
                }
                if(c == '+') {
                    break;
                }
                sb.append(c);
            }
            if(sb.length() != 0) {
                sb.append("@").append(dn);
                valid.add(sb.toString());
            }
        }

        return valid.size();
    }
}
