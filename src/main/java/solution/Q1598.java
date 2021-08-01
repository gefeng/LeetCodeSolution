package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Crawler Log Folder",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/crawler-log-folder/"
)
public class Q1598 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minOperations(String[] logs) {
        int depth = 0;
        for(String log : logs) {
            if(log.equals("../")) {
                depth = depth == 0 ? depth : depth - 1;
            } else if(log.equals("./")) {
                continue;
            } else {
                depth++;
            }
        }

        return depth;
    }
}
