package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "String Compression",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/string-compression/"
)
public class Q443 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int compress(char[] chars) {
        int write = 0;
        int count = 1;
        for(int i = 0; i < chars.length; ++i) {
            if(i == chars.length - 1 || chars[i] != chars[i + 1]) {
                chars[write++] = chars[i];
                if (count > 1) { //write count
                    for (char c : (count + "").toCharArray())
                        chars[write++] = c;
                }
                count = 1;
            } else
                count++;
        }
        return write;
    }
}
