package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Repeating Substring",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/maximum-repeating-substring/"
)
public class Q1668 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxRepeating(String sequence, String word) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 1; i <= 100; i++) {
            sb.append(word);
            if(sequence.contains(sb.toString())) {
                cnt = i;
            } else {
                break;
            }
        }

        return cnt;
    }
}
