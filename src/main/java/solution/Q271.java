package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Encode and Decode Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/encode-and-decode-strings/"
)
public class Q271 {
    /*
        make a header for offset calculation
    */
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        int m = strs.size();
        char[] header1 = new char[3];
        char[] header2 = new char[3 * m];

        for(int i = 2; i >= 0; i--) {
            header1[i] = (char)(m % 10 + '0');
            m = m / 10;
        }

        for(int i = 0; i < strs.size(); i++) {
            int n = strs.get(i).length();
            for(int j = 2; j >= 0; j--) {
                header2[i * 3 + j] = (char)(n % 10 + '0');
                n /= 10;
            }
        }


        StringBuilder sb = new StringBuilder();
        sb.append(new String(header1));
        sb.append(new String(header2));

        for(String s : strs) {
            sb.append(s);
        }
        System.out.println(sb);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ds = new ArrayList<>();

        int m = 0;
        for(int i = 0; i < 3; i++) {
            char c = s.charAt(i);
            m = m * 10 + c - '0';
        }

        int start = 3 + (m * 3);
        for(int i = 0; i < m; i++) {
            int len = 0;
            for(int j = 0; j < 3; j++) {
                len = len * 10 + s.charAt(3 + i * 3 + j) - '0';
            }

            ds.add(s.substring(start, start + len));
            start += len;
        }

        return ds;
    }
}
