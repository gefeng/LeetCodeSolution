package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Implement strStr()",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q28 {
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty())
            return 0;

        for(int i = 0; i < haystack.length(); ++i) {
            for(int j = 0; j < needle.length(); ++j) {
                if(i+j > haystack.length() - 1) return -1;
                if(haystack.charAt(i+j) != needle.charAt(j)) break;
                if(j == needle.length() - 1) return i;
            }
        }

        return -1;
    }
}
