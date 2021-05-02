package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Read N Characters Given Read4 II - Call multiple times",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/"
)
public class Q158 {
    private Queue<Character> localBuf = new ArrayDeque<>();

    public int read(char[] buf, int n) {
        int totalRead = 0;
        // localbuff should be cleaned before we call read4
        while(totalRead < n && !localBuf.isEmpty()) {
            buf[totalRead++] = localBuf.poll();
        }

        while(totalRead < n) {
            char[] buf4 = new char[4];
            int read4 = read4(buf4);
            if(read4 == 0) {
                break;
            }

            for(int i = 0; i < read4; i++) {
                if(totalRead < n) {
                    buf[totalRead++] = buf4[i];
                } else {
                    localBuf.offer(buf4[i]);
                }
            }
        }

        return totalRead;
    }

    int read4(char[] buf4) {
        return 0;
    }
}
