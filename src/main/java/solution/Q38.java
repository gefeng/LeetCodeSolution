package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count and Say",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/count-and-say/"
)
public class Q38 {
    public String countAndSay(int n) {
        if(n == 1)
            return "1";

        String prev = countAndSay(--n);
        String read = "";
        for(int i = 0; i < prev.length(); ++i) {
            char value = prev.charAt(i);
            int freq = 0;
            while((i + freq) < prev.length()) {
                if(value == prev.charAt(i + freq))
                    freq++;
                else
                    break;
            }
            i += (freq - 1);
            read += (Integer.toString(freq) + value);
        }
        return read;
    }
}
