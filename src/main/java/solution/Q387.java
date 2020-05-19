package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "First Unique Character in a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q387 {
    public int firstUniqChar(String s) {
        int[] counts = new int[26];
        s.chars().forEach(c -> counts[c-'a']++);

        for(int i = 0; i < s.length(); ++i) {
            if(counts[s.charAt(i)-'a'] == 1)
                return i;
        }
        return -1;
    }
}
