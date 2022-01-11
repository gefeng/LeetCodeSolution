package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Remove Comments",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/remove-comments/"
)
public class Q722 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();

        String s = toString(source);
        int n = s.length();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '/' && i + 1 < n && s.charAt(i + 1) == '/') {
                int j = i + 2;
                while(j < n && s.charAt(j) != '\n') {
                    j++;
                }
                if(i > 0 && s.charAt(i - 1) != '\n') {
                    sb.append('\n');
                }
                i = j;
            } else if(s.charAt(i) == '/' && i + 1 < n && s.charAt(i + 1) == '*') {
                int j = i + 3;
                while(j == 0 || s.charAt(j) != '/' || s.charAt(j - 1) != '*') {
                    j++;
                }
                i = j;
            } else {
                sb.append(s.charAt(i));
            }
        }

        String rs = sb.toString();
        int sz = rs.length();
        for(int i = 0; i < sz; i++) {
            StringBuilder row = new StringBuilder();
            int j = i;
            while(j < sz && rs.charAt(j) != '\n') {
                row.append(rs.charAt(j++));
            }
            if(row.length() != 0)
                ans.add(row.toString());
            i = j;
        }

        return ans;
    }

    private String toString(String[] source) {
        StringBuilder sb = new StringBuilder();

        for(String s : source) {
            sb.append(s).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
