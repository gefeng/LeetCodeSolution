package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Read N Characters Given Read4",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/read-n-characters-given-read4/"
)
public class Q157 {
    private int read4(char[] buf4) {
        return 0;
    }
    public int read(char[] buf, int n) {
        char[] buf4 = new char[4];
        int size = 0;
        int index = 0;
        do {
            size = read4(buf4);
            for(int i = 0; i < size && index < n; i++) {
                buf[index++] = buf4[i];
            }
        } while(size > 0 && index < n);

        return index;
    }
}
